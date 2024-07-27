package org.example.carcare;

import org.example.carcare.services.Services;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class CarCareApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarCareApplication.class, args);
    }


}
