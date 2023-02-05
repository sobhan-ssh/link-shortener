package com.snapp.linkshortener.redirectservice.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snapp.linkshortener.redirectservice.dto.AnalyticalResponseDto;
import com.snapp.linkshortener.redirectservice.dto.ClickRatioMessage;
import com.snapp.linkshortener.redirectservice.exception.NotFoundException;
import com.snapp.linkshortener.redirectservice.persistence.entity.URL;
import com.snapp.linkshortener.redirectservice.persistence.repository.URLRepository;
import com.snapp.linkshortener.redirectservice.service.IURLService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Service
public class URLServiceImpl implements IURLService {

    @Value(value = "${spring.kafka.url.click.ratio.topic.name}")
    private String urlClickRatioTopic;
    private final String SHORT_URL_DOMAIN = "https://snptrp.com/";

    private final URLRepository urlRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper = new ObjectMapper();

    public URLServiceImpl(URLRepository urlRepository, RedisTemplate<String, String> redisTemplate, KafkaTemplate<String, String> kafkaTemplate) {
        this.urlRepository = urlRepository;
        this.redisTemplate = redisTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }
    @Override
    public String getOriginalUrl(String shortUrl) {
        String originalLink;
        originalLink = redisTemplate.opsForValue().get(shortUrl);
        if (Objects.isNull(originalLink))  {
            Optional<URL> optional = urlRepository.findByShortLink(shortUrl);
            if (optional.isPresent()) {
                URL url = optional.get();
                originalLink = url.getMainLink();
                redisTemplate.opsForValue().set(url.getShortLink(), url.getMainLink(), 3, TimeUnit.MINUTES);
            } else
                throw new NotFoundException("URL NOT FOUND");
        }
        try {
            kafkaTemplate.send(urlClickRatioTopic, objectMapper.writeValueAsString(new ClickRatioMessage(shortUrl)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return originalLink;
    }

    @Override
    public AnalyticalResponseDto getUrlClickRate(String shortUrl) {
        Optional<URL> optional = urlRepository.findByShortLink(shortUrl);
        if (optional.isPresent()) {
            URL url = optional.get();
            return new AnalyticalResponseDto(SHORT_URL_DOMAIN + url.getShortLink(), url.getCreatedTime(), url.getClickCount());
        } else
            throw new NotFoundException("URL NOT FOUND");
    }
}
