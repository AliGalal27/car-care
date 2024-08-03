package org.example.carcare.booking.controller;

import org.example.carcare.booking.model.Booking;
import org.example.carcare.booking.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @DeleteMapping("/{bookingId}/cancel")
    public void cancelBooking(@PathVariable int bookingId) {
         bookingService.cancelBooking(bookingId);
    }

    @PutMapping("/{bookingId}/reschedule")
    public Booking rescheduleBooking(@PathVariable int bookingId, @RequestParam int newScheduleId) {
        return bookingService.rescheduleBooking(bookingId, newScheduleId);
    }


    @GetMapping("/history")
    public List<Booking> getBookingHistory(@RequestParam int userId) {
        return bookingService.getBookingHistoryForUser(userId);
    }

    // for admins
    @PutMapping("/{bookingId}/confirm")
    public Booking confirmBooking(@PathVariable int bookingId) {
        return bookingService.confirmBooking(bookingId);
    }

    @GetMapping
    public List<Booking> getAllBookings() {

        return bookingService.getAllBookings();
    }
    

}
