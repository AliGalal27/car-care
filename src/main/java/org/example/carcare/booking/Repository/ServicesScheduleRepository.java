package org.example.carcare.booking.Repository;

import org.example.carcare.booking.model.ServicesSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesScheduleRepository extends JpaRepository<ServicesSchedule, Integer> {


}
