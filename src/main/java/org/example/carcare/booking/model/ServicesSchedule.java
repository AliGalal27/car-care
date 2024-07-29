package org.example.carcare.booking.model;

import org.example.carcare.booking.enums.ServicesScheduleStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
@Table(name="services_schedule")

public class ServicesSchedule {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="ss_id", nullable = false)
        private Integer serviceScheduleId;

        @Column(name="service_id")
        private int serviceId;

        @Column(name="start_date")
        private Date startDate;

        @Column(name="end_date")
        private Date endDate;

        @Enumerated(EnumType.STRING)
        @Column(name = "status", nullable = false)
        private ServicesScheduleStatus servicesScheduleStatus;


}
