package com.superhero.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.superhero.entity.WikiMedia;
import com.superhero.repo.WikiMediaRepository;
import com.superhero.service.SuperHeroService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class KafkaEventProducer {

    String topic = "library-events";
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    SuperHeroService superHeroService;

    @Autowired
    ObjectMapper mapper;

    public CompletableFuture<SendResult<String,String>> sendWikiMediaEvents(CustomEvent event) throws JsonProcessingException {

        String key = event.getTitle();
        String value = objectMapper.writeValueAsString(event.getName());

        ProducerRecord<String,String> producerRecord = buildProducerRecord(key, value, topic);

        CompletableFuture<SendResult<String, String>> future =  kafkaTemplate.send(producerRecord) ;

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                handleSuccess(key, value, result);
            } else {
                handleFailure(key, value, ex);
            }
        });

        return future;
    }

    private ProducerRecord<String, String> buildProducerRecord(String key, String value, String topic) {
        List<Header> recordHeaders = List.of(new RecordHeader("event-source", "scanner".getBytes()));
        return new ProducerRecord<>(topic, key, value);
    }

    private void handleFailure(String key, String value, Throwable ex) {
        log.error("Error Sending the Message and the exception is {}", ex.getMessage());
        try {
            throw ex;
        } catch (Throwable throwable) {
            log.error("Error in OnFailure: {}", throwable.getMessage());
        }
    }

    private void handleSuccess(String key, String value, SendResult<String, String> result) {
       // log.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", key, value, result.getRecordMetadata().partition());
       superHeroService.saveWikiMediaData(value);
    }
}

