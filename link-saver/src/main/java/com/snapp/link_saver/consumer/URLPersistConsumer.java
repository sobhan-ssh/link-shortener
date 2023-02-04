package com.snapp.link_saver.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snapp.link_saver.dto.ClickRatioMessage;
import com.snapp.link_saver.dto.PersistURLMessage;
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
public class URLPersistConsumer {

    private final IURLService urlService;
    ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = "URL_PERSIST", containerFactory = "kafkaListenerContainerFactory")
    public void UrlPersistConsumer(List<ConsumerRecord<String, String>> records) {
        PersistURLMessage urlMessage = new PersistURLMessage();
        for (ConsumerRecord<String, String> stringStringConsumerRecord : records) {
            try {
                urlMessage = mapper.readValue(stringStringConsumerRecord.value(), PersistURLMessage.class);
                urlService.persistUrl(urlMessage);
            } catch (Exception e) {
                log.error("exception occurred when want to persist shortURL: {}, longURL: {}", urlMessage.getShortURL(), urlMessage.getOriginalURL());
            }
        }
    }

    @KafkaListener(topics = "URL_CLICK_RATIO", containerFactory = "kafkaListenerContainerFactory")
    public void UrlClickRatioConsumer(List<ConsumerRecord<String, String>> records) {
        ClickRatioMessage clickRatioMessage = new ClickRatioMessage();
        for (ConsumerRecord<String, String> stringStringConsumerRecord : records) {
            try {
                clickRatioMessage = mapper.readValue(stringStringConsumerRecord.value(), ClickRatioMessage.class);
                urlService.updateClickRatio(clickRatioMessage);
            } catch (Exception e) {
                log.error("exception occurred when want to update url click ration for shortURL: {}", clickRatioMessage.getShortURL());            }
        }
    }
}
