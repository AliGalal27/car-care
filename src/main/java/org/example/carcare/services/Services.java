package org.example.carcare.services;

import jakarta.persistence.*;

@Entity
@Table
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
    //region getters and setters
    public int getService_id() {
        return serviceid;
    }

    public void setService_id(int serviceid) {
        this.serviceid = serviceid;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    //endregion


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
