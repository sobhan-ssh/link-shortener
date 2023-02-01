package com.snapp.link_saver.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snapp.link_saver.dto.URLDto;
import com.snapp.link_saver.service.IURLService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class URLSaverConsumer {

    private final IURLService urlService;

    ObjectMapper mapper = new ObjectMapper();


    @KafkaListener(topics = "url-persist", containerFactory = "kafkaListenerContainerFactory")
    public void persistURLs(List<ConsumerRecord<String, String>> records) {
        for (int i = 0; i < records.size(); i++) {
            try {
                ConsumerRecord<String, String> record = records.get(i);
                URLDto urlDto = mapper.convertValue(record.value(), URLDto.class);
                urlService.persistUrl(urlDto);
            } catch (Exception e) {
                log.error("exception occurred when want to persist Url");
            }
        }
    }
}
