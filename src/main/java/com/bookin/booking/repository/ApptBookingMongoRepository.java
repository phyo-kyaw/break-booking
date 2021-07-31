package com.bookin.booking.repository;

import com.bookin.booking.model.AppointmentBooking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ApptBookingMongoRepository extends MongoRepository<AppointmentBooking, String> {
//    CalendarEvent findByColor(EventColor color){
//
//    };
    List<AppointmentBooking> findByBookingEntityGid(String gid);
    List<AppointmentBooking> findByStartBetween(LocalDateTime startPt, LocalDateTime endPt);
}
