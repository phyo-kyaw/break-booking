package com.booking.producer;

import com.booking.event.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}