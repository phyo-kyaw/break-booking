package com.booking.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BookedNotificationEvent extends BaseEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String bookerEmail;
    private String bookerName;
}