package com.bookin.booking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDateTime.parse;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class CalendarEventData {
    @Id
    //@GeneratedValue
    private String id;
    private  String title;
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private  String start;
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String end;
    private  EventColor color;


}
