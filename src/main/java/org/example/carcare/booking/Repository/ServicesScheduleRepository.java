package com.CarServiceProject.CarService.Repository;

import com.CarServiceProject.CarService.model.ServicesSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesScheduleRepository extends JpaRepository<ServicesSchedule, Integer> {


}
