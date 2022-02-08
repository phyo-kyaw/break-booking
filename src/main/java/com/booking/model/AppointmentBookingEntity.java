package com.booking.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDate;

import static java.time.LocalDateTime.parse;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AppointmentBookingEntity {
    @Id
//    @GeneratedValue
//    private String id;
    @JsonView(Views.Public.class)
    @Column(unique=true)
    private  String gid;
    @JsonView(Views.Public.class)
    private  String title_1;
    @JsonView(Views.Public.class)
    private  String title_2;
    @JsonView(Views.Public.class)
    private  String email;
    @JsonView(Views.Public.class)
    private  String phone;
    @JsonView(Views.Public.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate startDate;

    @JsonView(Views.Public.class)
    private  String minAdvanceBookingUnit;
    @JsonView(Views.Public.class)
    private  Long minAdvanceBooking;
    @JsonView(Views.Public.class)
    private  Long maxAdvanceBookingInDay;

    @JsonView(Views.Public.class)
    private  DurationM sessionM;
    @JsonView(Views.Public.class)
    private  DurationM intervalBreakM;

    @JsonView(Views.Public.class)
    private  TimeM amStartM;
    @JsonView(Views.Public.class)
    private  TimeM amEndM;

    @JsonView(Views.Public.class)
    private  TimeM pmStartM;
    @JsonView(Views.Public.class)
    private  TimeM pmEndM;

    @JsonView(Views.Public.class)
    private  WorkSession[] workingDays;

//    public void setStartDate(String startDate) {
//        this.startDate = parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//    }
//
//    public void setStartDate(LocalDate startDate) {
//        this.startDate = startDate;
//    }


}


