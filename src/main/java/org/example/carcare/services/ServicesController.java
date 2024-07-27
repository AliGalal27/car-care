package org.example.carcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/services")
public class ServicesController {

    private final ServicesService servicesService;

    @Autowired
    public ServicesController(ServicesService service) {
        this.servicesService = service;
    }


    @GetMapping
    public List<Services> getServices(){
        return servicesService.getServices();

    }

    @PostMapping
    public void addService(@RequestBody Services service) {
        servicesService.addNewService(service);
    }

    @DeleteMapping(path = "{serviceId}")
    public void deleteService(@PathVariable("serviceId") int Id) {
        servicesService.deleteService(Id);

    }


    @PutMapping("/{id}/{service_name}/{description}/{serviceType}/{price}")
    public Services updateService(
            @PathVariable int id,
            @PathVariable String service_name,
            @PathVariable String description,
            @PathVariable String serviceType,
            @PathVariable int price) {
        return servicesService.updateService(id, service_name, description, serviceType, price);
    }

    @GetMapping("/type/{serviceType}")
    public List<Services> getServicesByType(@PathVariable String serviceType) {
        return servicesService.getServicesByType(serviceType);
    }
}

