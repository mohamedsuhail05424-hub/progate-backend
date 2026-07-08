package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "properties")
public class Property 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String propertyName;

    private String type;

    private String location;

    private double purchasePrice;

    private double currentValue;

    private double rentalIncome;

    private double appreciationRate;

    public enum Status 
    {
        PENDING, APPROVED, RENTED, SOLD, AVAILABLE
    }

    @Enumerated(EnumType.STRING)
    private Status status;

    public Property() {
    }

    public Property(Long id, String propertyName, String type, String location, double purchasePrice,
            double currentValue, double rentalIncome, double appreciationRate, Status status) {
        this.id = id;
        this.propertyName = propertyName;
        this.type = type;
        this.location = location;
        this.purchasePrice = purchasePrice;
        this.currentValue = currentValue;
        this.rentalIncome = rentalIncome;
        this.appreciationRate = appreciationRate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public double getRentalIncome() {
        return rentalIncome;
    }

    public void setRentalIncome(double rentalIncome) {
        this.rentalIncome = rentalIncome;
    }

    public double getAppreciationRate() {
        return appreciationRate;
    }

    public void setAppreciationRate(double appreciationRate) {
        this.appreciationRate = appreciationRate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
}