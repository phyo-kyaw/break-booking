package com.booking.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
//@Profile("local")
public class AutoCreateConfig {

    @Bean
    public NewTopic emailEvents(){
        return TopicBuilder.name("appt-email-events")
                .partitions(1)
                .replicas(1)
                .build();
    }

}
