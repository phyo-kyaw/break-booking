package com.booking.controller;

import com.booking.event.BookedNotificationEvent;
import com.booking.mapper.ApptBookingMapper;
import com.booking.model.AppointmentBooking;
import com.booking.model.AppointmentBookingData;
import com.booking.model.Views;
import com.booking.producer.BookedNotificationEventProducer;
import com.booking.repository.ApptBookingMongoRepository;
import com.booking.service.EmailService;
import com.booking.webService.EmailWebService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    EmailWebService emailWebService;

    BookedNotificationEventProducer bookedNotificationEventProducer;

    @Autowired
    public ApptBookingController(ApptBookingMongoRepository apptBookingMongoRepository,
                                 ApptBookingMapper apptBookingMapper,
                                 EmailService emailService,
                                 EmailWebService emailWebService,
                                 BookedNotificationEventProducer bookedNotificationEventProducer) {
        this.apptBookingMongoRepository = apptBookingMongoRepository;
        this.apptBookingMapper = apptBookingMapper;
        this.emailService = emailService;
        this.emailWebService = emailWebService;
        this.bookedNotificationEventProducer = bookedNotificationEventProducer;
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
        System.out.println("booking");
        AppointmentBooking appointmentBooking =  apptBookingMapper.constructAppointmentBooking(booking);
        System.out.println(appointmentBooking);
        AppointmentBooking appointmentBookingSaved = apptBookingMongoRepository.save(appointmentBooking);
        System.out.println(appointmentBookingSaved);
        if(appointmentBookingSaved.getId() != null ){
            //emailService.sendNotificationEmail(appointmentBookingSaved.getBookerEmail(), "member");
            emailWebService.sendNotificationEmail(appointmentBookingSaved.getBookerEmail(), appointmentBookingSaved.getBookerName());
            System.out.println(appointmentBooking.getBookerEmail());

            BookedNotificationEvent bookedNotificationEvent = BookedNotificationEvent.builder()
                    .id( UUID.randomUUID().toString() )
                    .bookerEmail(appointmentBookingSaved.getBookerEmail())
                    .bookerName(appointmentBookingSaved.getBookerName()).build();

            bookedNotificationEventProducer.produce("email-events", bookedNotificationEvent);
        }
        return new AppointmentBooking();
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
