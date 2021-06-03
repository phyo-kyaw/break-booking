package com.bookin.booking.repository;

import com.bookin.booking.model.CalendarEvent;
import com.bookin.booking.model.EventColor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingMongoRepository extends MongoRepository<CalendarEvent, String> {
//    CalendarEvent findByColor(EventColor color){
//
//    };
}
