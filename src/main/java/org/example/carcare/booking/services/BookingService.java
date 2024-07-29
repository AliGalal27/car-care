package com.CarServiceProject.CarService.services;

import com.CarServiceProject.CarService.Repository.BookingRepository;
import com.CarServiceProject.CarService.Repository.ServicesScheduleRepository;
import com.CarServiceProject.CarService.enums.BookingStatus;
import com.CarServiceProject.CarService.enums.ServicesScheduleStatus;
import com.CarServiceProject.CarService.model.Booking;
import com.CarServiceProject.CarService.model.ServicesSchedule;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ServicesScheduleRepository servicesScheduleRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, ServicesScheduleRepository servicesScheduleRepository) {
        this.bookingRepository = bookingRepository;
        this.servicesScheduleRepository = servicesScheduleRepository;
    }

    @Transactional
    public Booking createBooking(Booking booking) {

        if (booking.getUserId() == 0 || booking.getServiceId() == 0 || booking.getServiceScheduleId() == 0) {
            throw new IllegalArgumentException("User ID, Service ID, and Service Schedule ID are required.");
        }

        ServicesSchedule selectedSchedule = servicesScheduleRepository.findById(booking.getServiceScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Service Schedule ID."));

        if(booking.getServiceId()!=selectedSchedule.getServiceId()) {
            throw new IllegalArgumentException("Service Schedule ID does not match service ID specified.");

        }
        if (selectedSchedule.getServicesScheduleStatus().equals(ServicesScheduleStatus.BOOKED))
            throw new IllegalArgumentException("The chosen slot is already booked, please choose a different service schedule.");

        booking.setBookingDate(new Date());
        booking.setBookingStatus(BookingStatus.PENDING);
        return bookingRepository.save(booking);
    }

    @Transactional
    public Booking rescheduleBooking(int bookingId, int newScheduleId) {

        Booking existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Booking ID."));


        ServicesSchedule newSchedule = servicesScheduleRepository.findById(newScheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Service Schedule ID."));

        if (existingBooking.getServiceId()!= newSchedule.getServiceId())
            throw new IllegalArgumentException("The new service schedule must be for the same service.");


        if (newSchedule.getServicesScheduleStatus().equals(ServicesScheduleStatus.BOOKED))
            throw new IllegalArgumentException("The chosen slot is already booked. Please choose a different service schedule.");

            existingBooking.setServiceScheduleId(newScheduleId);
            existingBooking.setBookingDate(new Date());


            return bookingRepository.save(existingBooking);
        }

        @Transactional
        public void cancelBooking( int bookingId){
            boolean bookingExists = bookingRepository.findById(bookingId).isPresent();
            if (bookingExists)
                bookingRepository.deleteById(bookingId);
        }


        public List<Booking> getBookingHistoryForUser ( int userId){
            return bookingRepository.findByUserId(userId);
        }

    }
