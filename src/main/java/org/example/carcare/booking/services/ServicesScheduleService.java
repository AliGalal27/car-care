package org.example.carcare.booking.services;

import jakarta.transaction.Transactional;
import org.example.carcare.booking.Repository.ServicesScheduleRepository;
import org.example.carcare.booking.model.ServicesSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesScheduleService {
    private final ServicesScheduleRepository servicesScheduleRepository;

    @Autowired
    public ServicesScheduleService(ServicesScheduleRepository servicesScheduleRepository) {
        this.servicesScheduleRepository = servicesScheduleRepository;
    }

    @Transactional
    public ServicesSchedule createServicesSchedule(ServicesSchedule servicesSchedule) {
        return servicesScheduleRepository.save(servicesSchedule);
    }

    public List<ServicesSchedule> getAllServicesSchedules() {
        return servicesScheduleRepository.findAll();
    }

    public ServicesSchedule getServicesScheduleById(int id) {
        return servicesScheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Service Schedule ID."));
    }

    @Transactional
    public void deleteServicesSchedule(int id) {
        if (servicesScheduleRepository.existsById(id)) {
            servicesScheduleRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Invalid Service Schedule ID.");
        }
    }
}
