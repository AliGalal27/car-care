package org.example.carcare.booking.controller;

import org.example.carcare.booking.model.ServicesSchedule;
import org.example.carcare.booking.services.ServicesScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services-schedule")
public class ServicesScheduleController {
    private final ServicesScheduleService servicesScheduleService;

    @Autowired
    public ServicesScheduleController(ServicesScheduleService servicesScheduleService) {
        this.servicesScheduleService = servicesScheduleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicesSchedule createServicesSchedule(@RequestBody ServicesSchedule servicesSchedule) {

        return servicesScheduleService.createServicesSchedule(servicesSchedule);
    }

    @GetMapping
    public List<ServicesSchedule> getAllServicesSchedules() {

        return servicesScheduleService.getAllServicesSchedules();
    }

    @GetMapping("/{id}")
    public ServicesSchedule getServicesScheduleById(@PathVariable int id) {

        return servicesScheduleService.getServicesScheduleById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServicesSchedule(@PathVariable int id) {

        servicesScheduleService.deleteServicesSchedule(id);
    }


}
