package com.booking.controller;

import com.booking.mapper.ApptBookingMapper;
import com.booking.model.AppointmentBookingEntity;
import com.booking.model.Views;
import com.booking.repository.ApptBookingEntityMongoRepository;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins={"http://localhost:4200", "http://localhost",
        "http://192.168.99.101", "http://ec2-3-104-154-174.ap-southeast-2.compute.amazonaws.com",
        "http://break-booking.online", "https://break-booking.online",
        "http://www.break-booking.online", "https://www.break-booking.online"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookingEntities")
public class ApptBookingEntityController {

    ApptBookingEntityMongoRepository apptBookingEntityMongoRepository;

    ApptBookingMapper apptBookingMapper;

    @Autowired
    public ApptBookingEntityController(
            ApptBookingEntityMongoRepository apptBookingEntityMongoRepository,
            ApptBookingMapper apptBookingMapper) {
        this.apptBookingEntityMongoRepository = apptBookingEntityMongoRepository;
        this.apptBookingMapper = apptBookingMapper;
    }

    @GetMapping
    @JsonView(Views.Public.class)
    public List<AppointmentBookingEntity> getAllBookingEntities() {
        return apptBookingEntityMongoRepository.findAll();
    }

    @GetMapping("/{gid}")
    @JsonView(Views.Public.class)
    public AppointmentBookingEntity getBookingEntity(@PathVariable String gid) {
        System.out.println(gid);
        return apptBookingEntityMongoRepository.findByGid(gid);
    }

    @PostMapping
    public AppointmentBookingEntity addBookingEntity(@RequestBody AppointmentBookingEntity appointmentBookingEntity) {
        //CalendarEvent calendarEvent =  bookingMapper.constructCalendarEvent(booking);
        System.out.println(appointmentBookingEntity);
        return apptBookingEntityMongoRepository.save(appointmentBookingEntity);
    }

    @PutMapping("/{gid}")
    public AppointmentBookingEntity updateBookingEntity(@PathVariable String gid, @RequestBody AppointmentBookingEntity appointmentBookingEntity) {
        //CalendarEvent calendarEvent =  bookingMapper.constructCalendarEvent(booking);
        System.out.println(appointmentBookingEntity);
        return apptBookingEntityMongoRepository.save(appointmentBookingEntity);
    }

    @DeleteMapping("/{gid}")
    public Long deleteBookingEntity(@PathVariable String gid) {
        return apptBookingEntityMongoRepository.deleteByGid(gid);
    }

    @GetMapping("/refreshDb")
    public void refresh() {
        apptBookingEntityMongoRepository.deleteAll();

    }
}
