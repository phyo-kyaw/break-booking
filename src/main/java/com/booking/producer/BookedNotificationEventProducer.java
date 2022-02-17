package com.booking.producer;


import com.booking.event.BaseEvent;
import com.booking.event.BookedNotificationEvent;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@Service
@Slf4j
public class BookedNotificationEventProducer implements EventProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    String value = "";

//    @Override
//    public void produce(String topic, BaseEvent event) {
//        this.kafkaTemplate.send(topic, event);
//    }


    @Override
    public void produce(String topic, BaseEvent event)  {

        String key = event.getId();

        try {
            value = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //ProducerRecord<Long,String> producerRecord = buildProducerRecord(key, value, topic);

        ListenableFuture<SendResult<String, Object>> listenableFuture =  kafkaTemplate.send(topic, key, value);
        //kafkaTemplate.send(producerRecord);

        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                handleFailure(key, value, ex);
            }

            @Override
            public void onSuccess(SendResult<String,Object> result) {
                handleSuccess(key, value, result);
            }
        });
    }

//    private ProducerRecord<Long, String> buildProducerRecord(String key, String value, String topic) {
//
//
//        //List<Header> recordHeaders = List.of(new RecordHeader("event-source", "scanner".getBytes()));
//
//        return new ProducerRecord<>(topic, null, key, value); //, null); //recordHeaders);
//    }

    private void handleFailure(String key, String value, Throwable ex) {
        log.error("Error Sending the Message and the exception is {}", ex.getMessage());
        try {
            throw ex;
        } catch (Throwable throwable) {
            log.error("Error in OnFailure: {}", throwable.getMessage());
        }


    }

    private void handleSuccess(String key, String value, SendResult<String, Object> result) {
        log.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", key, value, result.getRecordMetadata().partition());
    }

}
