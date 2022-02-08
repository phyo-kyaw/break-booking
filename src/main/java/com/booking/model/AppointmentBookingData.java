package com.booking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import static java.time.LocalDateTime.parse;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class AppointmentBookingData {
    @Id
    //@GeneratedValue
    private String id;
    private  String title;
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private  String start;
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String end;
    private  EventColor color;
    private  String bookingEntityGid;
    private  String bookingEntityName;
    private  String bookerEmail;
    private  String bookerPhone;
    private  String bookerName;
    private Meta meta;


}
