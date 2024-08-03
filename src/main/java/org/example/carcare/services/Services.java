package org.example.carcare.services;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Data
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private int serviceid;
    private String service_name;
    private String description;
    @Column(name = "service_type")
    private String serviceType;
    private int price;
    @Column(name = "image_url")
    private String image_url;

    public Services() {}

    public Services(int serviceid, String service_name, String description, String serviceType, int price) {
        this.serviceid = serviceid;
        this.service_name = service_name;
        this.description = description;
        this.serviceType = serviceType;
        this.price = price;
    }

    public Services(String service_name, String description, String serviceType, int price) {
        this.service_name = service_name;
        this.description = description;
        this.serviceType = serviceType;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Services{" +
                "service_id=" + serviceid +
                ", service_name='" + service_name + '\'' +
                ", description='" + description + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", price=" + price +
                '}';
    }
}
