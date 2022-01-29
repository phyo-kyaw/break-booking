package com.bookin.booking.controller;

import com.bookin.booking.mapper.ApptBookingMapper;
import com.bookin.booking.model.AppointmentBooking;
import com.bookin.booking.model.AppointmentBookingData;
import com.bookin.booking.model.Views;
import com.bookin.booking.repository.ApptBookingMongoRepository;
import com.bookin.booking.service.EmailService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins={"http://localhost:4200", "http://localhost",
        "http://192.168.99.101", "http://ec2-3-104-154-174.ap-southeast-2.compute.amazonaws.com",
        "http://break-booking.online", "https://break-booking.online",
        "http://www.break-booking.online", "https://www.break-booking.online"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class ApptBookingController {

    ApptBookingMongoRepository apptBookingMongoRepository;

    ApptBookingMapper apptBookingMapper;

    EmailService emailService;

    @Autowired
    public ApptBookingController(ApptBookingMongoRepository apptBookingMongoRepository,
                                 ApptBookingMapper apptBookingMapper,
                                 EmailService emailService) {
        this.apptBookingMongoRepository = apptBookingMongoRepository;
        this.apptBookingMapper = apptBookingMapper;
        this.emailService = emailService;
    }

    @GetMapping
    @JsonView(Views.Public.class)
    public List<AppointmentBooking> getAllBookings() {

        return apptBookingMongoRepository.findAll();
        //return apptBookingMongoRepository.findByStartBetween(LocalDateTime.parse("2021-08-02T00:00:00"), LocalDateTime.parse("2021-08-04T00:00:00"));
    }

    @GetMapping("/gid/{gid}")
    @JsonView(Views.Public.class)
    public List<AppointmentBooking> getBookingsByGid(@PathVariable String gid) {
        return apptBookingMongoRepository.findByBookingEntityGid(gid);
    }


    @GetMapping("/{id}")
    @JsonView(Views.Public.class)
    public AppointmentBooking getBookingById(@PathVariable String id) {
        return apptBookingMongoRepository.findById(id).get();

    }

    @PostMapping
    public AppointmentBooking addBooking(@RequestBody AppointmentBookingData booking) {
        AppointmentBooking appointmentBooking =  apptBookingMapper.constructAppointmentBooking(booking);
        AppointmentBooking appointmentBookingSaved = apptBookingMongoRepository.save(appointmentBooking);
        if(appointmentBookingSaved.getId() != null ){
            //emailService.sendNotificationEmail(appointmentBookingSaved.getBookerEmail(), "member");
            System.out.println(appointmentBookingSaved.getBookerEmail());
        }
        return appointmentBookingSaved;
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable String id) {
        apptBookingMongoRepository.deleteById(id);
    }

    @GetMapping("/refreshDb")
    public void refresh() {
        apptBookingMongoRepository.deleteAll();
    }
}
