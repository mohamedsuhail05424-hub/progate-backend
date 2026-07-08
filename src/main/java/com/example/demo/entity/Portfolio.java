package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "portfolios")
public class Portfolio 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalInvestment;

    private double totalIncome;

    private double rentalYield;

    private double occupancyRate;

    public Portfolio() {
    }

    public Portfolio(Long id, double totalInvestment, double totalIncome, double rentalYield, double occupancyRate) {
        this.id = id;
        this.totalInvestment = totalInvestment;
        this.totalIncome = totalIncome;
        this.rentalYield = rentalYield;
        this.occupancyRate = occupancyRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(double totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getRentalYield() {
        return rentalYield;
    }

    public void setRentalYield(double rentalYield) {
        this.rentalYield = rentalYield;
    }

    public double getOccupancyRate() {
        return occupancyRate;
    }

    public void setOccupancyRate(double occupancyRate) {
        this.occupancyRate = occupancyRate;
    }

}
