package com.OneFood.ServerOneFood.model;

import javax.persistence.*;

@Table
@Entity
public class OrderFoodDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idOrderFoodDetails;
    private long idFood;
    private long idBill;
    private int numberOfFood;
    private String foodPrice;

    public OrderFoodDetails(long idFood, long idBill, int numberOfFood, String foodPrice) {
        this.idFood = idFood;
        this.idBill = idBill;
        this.numberOfFood = numberOfFood;
        this.foodPrice = foodPrice;
    }

    public OrderFoodDetails() {
    }

    public long getIdOrderFoodDetails() {
        return idOrderFoodDetails;
    }

    public void setIdOrderFoodDetails(long idOrderFoodDetails) {
        this.idOrderFoodDetails = idOrderFoodDetails;
    }

    public long getIdFood() {
        return idFood;
    }

    public void setIdFood(long idFood) {
        this.idFood = idFood;
    }

    public long getIdBill() {
        return idBill;
    }

    public void setIdBill(long idBill) {
        this.idBill = idBill;
    }

    public int getNumberOfFood() {
        return numberOfFood;
    }

    public void setNumberOfFood(int numberOfFood) {
        this.numberOfFood = numberOfFood;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }
}
