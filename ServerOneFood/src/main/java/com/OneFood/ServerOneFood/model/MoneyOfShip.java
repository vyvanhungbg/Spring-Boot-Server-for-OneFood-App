package com.OneFood.ServerOneFood.model;


import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Table
@Entity
public class MoneyOfShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMoneyOfShip;
    private String price;
    private Double underTheDistance;
    private Double overTheDistance;

    public MoneyOfShip(long idMoneyOfShip, String price, Double underTheDistance, Double overTheDistance) {
        this.idMoneyOfShip = idMoneyOfShip;
        this.price = price;
        this.underTheDistance = underTheDistance;
        this.overTheDistance = overTheDistance;
    }

    public MoneyOfShip() {
    }

    public long getIdMoneyOfShip() {
        return idMoneyOfShip;
    }

    public void setIdMoneyOfShip(long idMoneyOfShip) {
        this.idMoneyOfShip = idMoneyOfShip;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Double getUnderTheDistance() {
        return underTheDistance;
    }

    public void setUnderTheDistance(Double underTheDistance) {
        this.underTheDistance = underTheDistance;
    }

    public Double getOverTheDistance() {
        return overTheDistance;
    }

    public void setOverTheDistance(Double overTheDistance) {
        this.overTheDistance = overTheDistance;
    }
}
