package com.booking.producer;


import com.booking.event.BaseEvent;
import com.booking.event.BookedNotificationEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookedNotificationEventProducer implements EventProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void produce(String topic, BaseEvent event) {
        this.kafkaTemplate.send(topic, event);
    }


//    public void sendBookedNotificationEvent(String topic, BaseEvent event) throws JsonProcessingException {
//
//        Integer key = libraryEvent.getLibraryEventId();
//        String value = objectMapper.writeValueAsString(libraryEvent);
//
//        ProducerRecord<Integer,String> producerRecord = buildProducerRecord(key, value, topic);
//
//        ListenableFuture<SendResult<Integer,String>> listenableFuture =  kafkaTemplate.send(producerRecord);
//
//        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
//            @Override
//            public void onFailure(Throwable ex) {
//                handleFailure(key, value, ex);
//            }
//
//            @Override
//            public void onSuccess(SendResult<Integer, String> result) {
//                handleSuccess(key, value, result);
//            }
//        });
//    }

}
