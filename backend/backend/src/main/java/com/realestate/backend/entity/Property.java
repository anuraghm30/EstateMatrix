package com.realestate.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String address;
    private String city;
    private String type;
    private Double price;
    private String description;
    private String location;

    public Property() {}

    public Property(String title, String address, String city, String type,
                    Double price, String description, String location) {
        this.title = title;
        this.address = address;
        this.city = city;
        this.type = type;
        this.price = price;
        this.description = description;
        this.location = location;
    }

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}



