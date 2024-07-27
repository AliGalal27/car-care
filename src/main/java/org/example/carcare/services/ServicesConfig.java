package org.example.carcare.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ServicesConfig {

    @Bean
    CommandLineRunner commandLineRunner(ServicesRepository repository){
        return args -> {
            Services tirePressure =  new Services(
                    "tire pressuree",
                    "adjust tire pressure",
                    "maintenance",
                    50

            );
            Services clean =  new Services(
                    "car cleann",
                    "car cleaning",
                    "maintenance",
                    100

            );
//            repository.saveAll(List.of(tirePressure,clean));

            };
    }
}
