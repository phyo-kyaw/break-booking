package com.bookin.booking.mapper;

import com.bookin.booking.model.CalendarEvent;
import com.bookin.booking.model.CalendarEventData;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class BookingMapper {

    public BookingMapper() {
    }

    public CalendarEvent constructCalendarEvent(CalendarEventData calendarEventData){

        CalendarEvent calendarEvent = new CalendarEvent();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        calendarEvent.setId(calendarEventData.getId());
        calendarEvent.setTitle(calendarEventData.getTitle());
        calendarEvent.setStart(LocalDateTime.parse(calendarEventData.getStart(), formatter));
        calendarEvent.setEnd(LocalDateTime.parse(calendarEventData.getEnd(), formatter));
        calendarEvent.setColor(calendarEventData.getColor());
        return calendarEvent;
    }
}
