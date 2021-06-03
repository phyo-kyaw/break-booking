package com.bookin.booking;

import com.bookin.booking.model.CalendarEvent;
import com.bookin.booking.model.EventColor;
import com.bookin.booking.repository.BookingMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.time.LocalTime.now;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BookingApplication {

    @Autowired
    BookingMongoRepository bookingMongoRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookingApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void afterTheStart() {

        //bookingMongoRepository.deleteAll();
        System.out.println("the application started...");
        EventColor ec = new EventColor();
        ec.setPrimary("#ad2121");
        ec.setSecondary("#FAE3E3");


        CalendarEvent cE = new CalendarEvent();
        cE.setColor(ec);
        cE.setStart("2021-05-22 14:45:00");
        cE.setEnd("2021-05-22 15:45:00");
        cE.setTitle("");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        CalendarEvent cE1 = new CalendarEvent();
        cE1.setColor(ec);
        cE1.setStart(LocalDateTime.parse("2021-05-23T12:00:00.000Z", formatter));
        cE1.setEnd(LocalDateTime.parse("2021-05-23T13:00:00.000Z", formatter));
        cE1.setTitle("");


        System.out.println("saving user to the db");
        //bookingMongoRepository.save(cE);
        //bookingMongoRepository.save(cE1);
        System.out.println("getting users from the db");
        bookingMongoRepository.findAll().forEach(System.out::println);
        bookingMongoRepository.findAll().forEach(x->System.out.println(x.getColor()));

        System.out.println("getting user by email");
        //System.out.println(bookingMongoRepository.findFirstByEmail("test1@gmail.com"));;
    }
}
