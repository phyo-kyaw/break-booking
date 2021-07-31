package com.bookin.booking.model;

import com.fasterxml.jackson.annotation.JsonView;
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
public class AppointmentBooking {
    @Id
    //@GeneratedValue
    @JsonView(Views.Public.class)
    private String id;
    @JsonView(Views.Public.class)
    private  String title;
    @JsonView(Views.Public.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private  LocalDateTime start;
    @JsonView(Views.Public.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime end;
    @JsonView(Views.Public.class)
    private  EventColor color;
    @JsonView(Views.Public.class)
    private  String bookingEntityGid;
    @JsonView(Views.Public.class)
    private  String bookingEntityName;
    @JsonView(Views.Public.class)
    private  String bookerEmail;
    @JsonView(Views.Public.class)
    private  String bookerPhone;
    @JsonView(Views.Public.class)
    private  String bookerName;
    @JsonView(Views.Public.class)
    private  Meta meta;

    public void setStart(String start) {
        this.start = parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
