package com.bookin.booking.controller;

import com.bookin.booking.mapper.BookingMapper;
import com.bookin.booking.model.CalendarEvent;
import com.bookin.booking.model.CalendarEventData;
import com.bookin.booking.model.Views;
import com.bookin.booking.repository.BookingMongoRepository;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin(origins={"http://localhost:4200", "http://3.104.154.174", "http://3.104.154.174:80"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {

    BookingMongoRepository bookingMongoRepository;

    BookingMapper bookingMapper;

    @Autowired
    public BookingController(BookingMongoRepository bookingMongoRepository, BookingMapper bookingMapper) {
        this.bookingMongoRepository = bookingMongoRepository;
        this.bookingMapper = bookingMapper;
    }

    @GetMapping
    @JsonView(Views.Public.class)
    public List<CalendarEvent> getAllBookings() {
        return bookingMongoRepository.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(Views.Public.class)
    public CalendarEvent getBookingById(@PathVariable String id) {
        return bookingMongoRepository.findById(id).get();
    }

    @PostMapping
    public CalendarEvent addBooking(@RequestBody CalendarEventData booking) {
        CalendarEvent calendarEvent =  bookingMapper.constructCalendarEvent(booking);
        return bookingMongoRepository.save(calendarEvent);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable String id) {
        bookingMongoRepository.deleteById(id);
    }

    @GetMapping("/refreshDb")
    public void refresh() {
        bookingMongoRepository.deleteAll();

    }
}
