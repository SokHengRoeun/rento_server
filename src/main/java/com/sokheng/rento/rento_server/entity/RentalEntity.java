package com.sokheng.rento.rento_server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class RentalEntity {
    @Id
    @Column(name = "rental_id", nullable = false)
    private String rentalId;

    @Column(nullable = false)
    private String name;

    @Column(name = "admin_id", nullable = false)
    private String adminId;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String description;

    @Column(name = "publish_date", nullable = false)
    private String publishDate;

    @Column(nullable = true)
    private String dateline;

    @Column(nullable = false)
    private String status;

    @Column(name = "property_type", nullable = false)
    private String propertyType;

    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    public RentalEntity() {
    }

    public RentalEntity(String rentalId, String name, String adminId, float price, String location, String description, String publish_date, String dateline, String status, String propertyType, String customerId, String imageUrl) {
        this.rentalId = rentalId;
        this.name = name;
        this.adminId = adminId;
        this.price = price;
        this.location = location;
        this.description = description;
        this.publishDate = publish_date;
        this.dateline = dateline;
        this.status = status;
        this.propertyType = propertyType;
        this.customerId = customerId;
        this.imageUrl = imageUrl;
    }

    public String getRentalId() {
        return rentalId;
    }

    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publish_date) {
        this.publishDate = publish_date;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
