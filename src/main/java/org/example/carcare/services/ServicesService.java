package org.example.carcare.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesService {

    private final ServicesRepository servicesRepository;

    @Autowired
    public ServicesService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }


    public List<Services> getServices(){
        return servicesRepository.findAll();
    }

    public void addNewService(Services service) {
        Optional<Services> serviceOptional = servicesRepository.findByServiceid(service.getServiceid());
        if (serviceOptional.isPresent()) {
            throw new IllegalStateException("Service already exists");
        }
        else {
            servicesRepository.save(service);
            System.out.println("service added");
        }
    }
    public void deleteService(int id) {
        Optional<Services> serviceOptional = servicesRepository.findById(id);
        if (serviceOptional.isEmpty()) {
            throw new IllegalStateException("Service not found");
        }
        else {
            servicesRepository.deleteById(id);
        }
    }
    @Transactional
    public Services updateService(
            int serviceId, String serviceName, String description, String serviceType, int price,String imageUrl) {
        Optional<Services> optionalService = servicesRepository.findById(serviceId);
        if (optionalService.isPresent()) {
            Services existingService = optionalService.get();
            if (serviceName != null) existingService.setService_name(serviceName);
            if (description != null) existingService.setDescription(description);
            if (serviceType != null) existingService.setServiceType(serviceType);
            if (imageUrl != null) existingService.setImage_url(imageUrl); // Update image URL

            existingService.setPrice(price);
            return servicesRepository.save(existingService);
        } else {
            throw new RuntimeException("Service not found with id: " + serviceId);
        }
    }

    public List<Services> getServicesByType(String serviceType) {
        return servicesRepository.findByServiceType(serviceType);
    }
    public Services getServiceById(int id) {
        Optional<Services> serviceOptional = servicesRepository.findById(id);
        if (serviceOptional.isPresent()) {
            return serviceOptional.get();
        } else {
            throw new RuntimeException("Service with id: " + id + "could not found");
        }
    }
}

