package org.example.carcare.booking.model;

import org.example.carcare.booking.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Entity
@Data
@Table(name="bookings")
public class Booking {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name="booking_id", nullable = false)
 private int bookingId;


 @Column(name="user_id", nullable = false)
 private int userId;


 @Column(name="service_id", nullable = false)
 private int serviceId;

 @Column(name="service_schedule_id", nullable = false)
 private int serviceScheduleId;

 @Column(name="booking_date", updatable = false)
 @Temporal(TemporalType.TIMESTAMP)
 private Date bookingDate;

 @Enumerated(EnumType.STRING)
 @Column(name="status", updatable = false)
 private BookingStatus bookingStatus;

 @PrePersist
 protected void onCreate() {
  bookingDate = new Date();
  bookingStatus = BookingStatus.PENDING;
 }


}
