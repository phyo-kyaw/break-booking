package com.booking.repository;

import com.booking.model.AppointmentBookingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApptBookingEntityMongoRepository extends MongoRepository<AppointmentBookingEntity, String> {
//    CalendarEvent findByColor(EventColor color){
//
//    };
    AppointmentBookingEntity findByGid(String gid);

    Long deleteByGid(String gid);
}
