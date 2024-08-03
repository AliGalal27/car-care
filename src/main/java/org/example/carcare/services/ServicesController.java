package org.example.carcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping(path = "CarServices")
public class ServicesController {

    private final ServicesService servicesService;

    @Autowired
    public ServicesController(ServicesService service) {
        this.servicesService = service;
    }

    //where image will be stored
    @Value("${upload.path}")
    private String uploadPath;


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


    @PutMapping("/{id}/{service_name}/{description}/{serviceType}/{price}/{imageUrl}")
    public Services updateService(
            @PathVariable int id,
            @PathVariable String service_name,
            @PathVariable String description,
            @PathVariable String serviceType,
            @PathVariable int price,
            @PathVariable String imageUrl) {
        return servicesService.updateService(id, service_name, description, serviceType, price,imageUrl);
    }

    @GetMapping("/type/{serviceType}")
    public List<Services> getServicesByType(@PathVariable String serviceType) {
        return servicesService.getServicesByType(serviceType);
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<String> uploadImage(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) {
        try {
            // Check and create directory if not exists
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // Handle file name safely
            String fileName = file.getOriginalFilename();
            if (fileName == null || fileName.isEmpty()) {
                return ResponseEntity.badRequest().body("Invalid file name");
            }
            Path filePath = uploadDir.resolve(fileName);

            // Save file to the directory
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Save relative path to the database
            String relativePath = "assets/" + fileName;

            Services service = servicesService.getServiceById(id); // Fetch service by ID
            if (service != null) {
                service.setImage_url(relativePath); // Update with relative path
                servicesService.updateService(service.getServiceid(), service.getService_name(), service.getDescription(), service.getServiceType(), service.getPrice(), relativePath);
                return ResponseEntity.ok("Image uploaded and saved successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service not found");
            }
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }


}

