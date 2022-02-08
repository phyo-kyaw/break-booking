package com.booking.mapper;

import com.booking.model.AppointmentBooking;
import com.booking.model.AppointmentBookingData;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class ApptBookingMapper {

    public ApptBookingMapper() {
    }

    public AppointmentBooking constructAppointmentBooking(AppointmentBookingData appointmentBookingData){

        AppointmentBooking appointmentBooking = new AppointmentBooking();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        appointmentBooking.setId(appointmentBookingData.getId());
        appointmentBooking.setTitle(appointmentBookingData.getTitle());
        appointmentBooking.setStart(LocalDateTime.parse(appointmentBookingData.getStart(), formatter));
        appointmentBooking.setEnd(LocalDateTime.parse(appointmentBookingData.getEnd(), formatter));
        appointmentBooking.setColor(appointmentBookingData.getColor());
        appointmentBooking.setBookingEntityGid(appointmentBookingData.getBookingEntityGid());
        appointmentBooking.setBookingEntityName(appointmentBookingData.getBookingEntityName());
        appointmentBooking.setBookerEmail(appointmentBookingData.getBookerEmail());
        appointmentBooking.setBookerPhone(appointmentBookingData.getBookerPhone());
        appointmentBooking.setBookerName(appointmentBookingData.getBookerName());
        appointmentBooking.setMeta(appointmentBookingData.getMeta());

        return appointmentBooking;
    }
}
