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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<Map<String, String>> addService(@RequestBody Services service) {
        servicesService.addNewService(service);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Service added successfully");
        return ResponseEntity.ok(response);
    }


    @DeleteMapping(path = "{serviceId}")
    public void deleteService(@PathVariable("serviceId") int Id) {
        servicesService.deleteService(Id);

    }

    @PutMapping("/{id}")
    public Services updateService(
            @PathVariable int id,
            @RequestBody Services updatedService) {
        return servicesService.updateService(id, updatedService);
    }

    @GetMapping("/type/{serviceType}")
    public List<Services> getServicesByType(@PathVariable String serviceType) {
        return servicesService.getServicesByType(serviceType);
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<Map<String, String>> uploadImage(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) {
        Map<String, String> response = new HashMap<>();

        try {
            // Check and create directory if not exists
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // Handle file name safely
            String fileName = file.getOriginalFilename();
            if (fileName == null || fileName.isEmpty()) {
                response.put("message", "Invalid file name");
                return ResponseEntity.badRequest().body(response);
            }
            Path filePath = uploadDir.resolve(fileName);

            // Save file to the directory
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Save relative path to the database
            String relativePath = "assets/" + fileName;

            Services service = servicesService.getServiceById(id); // Fetch service by ID
            if (service != null) {
                service.setImage_url(relativePath); // Update with relative path
                servicesService.updateService(service.getServiceid(),service);
                response.put("message", "Image uploaded and saved successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Service not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);            }
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace for debugging
            response.put("message", "Failed to upload image");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/types")
    public List<String> getServiceTypes() {
        return servicesService.getDistinctServiceTypes();
    }


}

