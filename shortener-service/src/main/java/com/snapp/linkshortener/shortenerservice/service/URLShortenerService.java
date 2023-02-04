package com.snapp.linkshortener.shortenerservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snapp.linkshortener.shortenerservice.dto.PersistURLMessage;
import com.snapp.linkshortener.shortenerservice.dto.ShortURLRequestDto;
import com.snapp.linkshortener.shortenerservice.dto.ShortURLResponseDto;
import com.snapp.linkshortener.shortenerservice.util.URLValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.CheckedOutputStream;

@Service
public class URLShortenerService {

    @Value(value = "${spring.kafka.persist.url.topic.name}")
    private String persistUrlTopic;

    private final String SHORT_URL_DOMAIN = "https://snptrp.com/";
    private final Long START_RANGE;
    private final Long END_RANGE;

    private AtomicLong counter;
    private final Base62Convertor convertor;
    private ObjectMapper objectMapper = new ObjectMapper();
    private final RedisTemplate<String, String> redisTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public URLShortenerService(Base62Convertor convertor, RedisTemplate<String, String> redisTemplate, KafkaTemplate<String, String> kafkaTemplate) {
        START_RANGE = 100000000L;
        END_RANGE = 1000000000L;
        counter = new AtomicLong(START_RANGE);
        this.convertor = convertor;
        this.redisTemplate = redisTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }

    public ShortURLResponseDto generateShortURL(ShortURLRequestDto requestDto) {
        long uniqueID = counter.getAndAdd(1);
        if (URLValidator.INSTANCE.validateURL(requestDto.getOriginalURL()) && uniqueID < END_RANGE && uniqueID >= START_RANGE){
            try {
                String shortURL = convertor.encode(uniqueID);
                ShortURLResponseDto responseDto = new ShortURLResponseDto(requestDto.getOriginalURL(),SHORT_URL_DOMAIN + shortURL);
                redisTemplate.opsForValue().set(shortURL, requestDto.getOriginalURL());
                kafkaTemplate.send(persistUrlTopic, objectMapper.writeValueAsString(new PersistURLMessage(requestDto.getOriginalURL(), shortURL)));
                return responseDto;
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else
            throw new RuntimeException("");
    }

//    public String getOriginalURL(String shortURL) {
//
//    }
}
