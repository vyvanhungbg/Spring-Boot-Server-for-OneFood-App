package com.OneFood.ServerOneFood.Model;

import javax.persistence.*;

@Entity
@Table(name = "food_discount_cde")
public class FoodDiscountCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFoodDiscountCode;
    private long idStore;
    private long idFood;
    private long idTypeOfDiscountCode;
    private int foodDiscountCodeIsDestroy;
    int foodDiscountCodeStatus;
    private String foodDiscountCodeByPaymentMethod;
    private String foodDiscountCodeDescribe;
    private String foodDiscountCodeImage;
    private String foodDiscountEndTime;
    private String foodDiscountStartTime;
    private String foodDiscountCodeByMoney;
    private String foodDiscountCodeByPercent;

    public FoodDiscountCode() {
    }

    public FoodDiscountCode( long idStore, long idFood, long idTypeOfDiscountCode, int foodDiscountCodeIsDestroy, int foodDiscountCodeStatus, String foodDiscountCodeByPaymentMethod, String foodDiscountCodeDescribe, String foodDiscountCodeImage, String foodDiscountEndTime, String foodDiscountStartTime, String foodDiscountCodeByMoney, String foodDiscountCodeByPercent) {
        this.idStore = idStore;
        this.idFood = idFood;
        this.idTypeOfDiscountCode = idTypeOfDiscountCode;
        this.foodDiscountCodeIsDestroy = foodDiscountCodeIsDestroy;
        this.foodDiscountCodeStatus = foodDiscountCodeStatus;
        this.foodDiscountCodeByPaymentMethod = foodDiscountCodeByPaymentMethod;
        this.foodDiscountCodeDescribe = foodDiscountCodeDescribe;
        this.foodDiscountCodeImage = foodDiscountCodeImage;
        this.foodDiscountEndTime = foodDiscountEndTime;
        this.foodDiscountStartTime = foodDiscountStartTime;
        this.foodDiscountCodeByMoney = foodDiscountCodeByMoney;
        this.foodDiscountCodeByPercent = foodDiscountCodeByPercent;
    }

    public long getIdFoodDiscountCode() {
        return idFoodDiscountCode;
    }

    public void setIdFoodDiscountCode(long idFoodDiscountCode) {
        this.idFoodDiscountCode = idFoodDiscountCode;
    }

    public long getIdStore() {
        return idStore;
    }

    public void setIdStore(long idStore) {
        this.idStore = idStore;
    }

    public long getIdFood() {
        return idFood;
    }

    public void setIdFood(long idFood) {
        this.idFood = idFood;
    }

    public long getIdTypeOfDiscountCode() {
        return idTypeOfDiscountCode;
    }

    public void setIdTypeOfDiscountCode(long idTypeOfDiscountCode) {
        this.idTypeOfDiscountCode = idTypeOfDiscountCode;
    }

    public int getFoodDiscountCodeIsDestroy() {
        return foodDiscountCodeIsDestroy;
    }

    public void setFoodDiscountCodeIsDestroy(int foodDiscountCodeIsDestroy) {
        this.foodDiscountCodeIsDestroy = foodDiscountCodeIsDestroy;
    }

    public int getFoodDiscountCodeStatus() {
        return foodDiscountCodeStatus;
    }

    public void setFoodDiscountCodeStatus(int foodDiscountCodeStatus) {
        this.foodDiscountCodeStatus = foodDiscountCodeStatus;
    }

    public String getFoodDiscountCodeByPaymentMethod() {
        return foodDiscountCodeByPaymentMethod;
    }

    public void setFoodDiscountCodeByPaymentMethod(String foodDiscountCodeByPaymentMethod) {
        this.foodDiscountCodeByPaymentMethod = foodDiscountCodeByPaymentMethod;
    }

    public String getFoodDiscountCodeDescribe() {
        return foodDiscountCodeDescribe;
    }

    public void setFoodDiscountCodeDescribe(String foodDiscountCodeDescribe) {
        this.foodDiscountCodeDescribe = foodDiscountCodeDescribe;
    }

    public String getFoodDiscountCodeImage() {
        return foodDiscountCodeImage;
    }

    public void setFoodDiscountCodeImage(String foodDiscountCodeImage) {
        this.foodDiscountCodeImage = foodDiscountCodeImage;
    }

    public String getFoodDiscountEndTime() {
        return foodDiscountEndTime;
    }

    public void setFoodDiscountEndTime(String foodDiscountEndTime) {
        this.foodDiscountEndTime = foodDiscountEndTime;
    }

    public String getFoodDiscountStartTime() {
        return foodDiscountStartTime;
    }

    public void setFoodDiscountStartTime(String foodDiscountStartTime) {
        this.foodDiscountStartTime = foodDiscountStartTime;
    }

    public String getFoodDiscountCodeByMoney() {
        return foodDiscountCodeByMoney;
    }

    public void setFoodDiscountCodeByMoney(String foodDiscountCodeByMoney) {
        this.foodDiscountCodeByMoney = foodDiscountCodeByMoney;
    }

    public String getFoodDiscountCodeByPercent() {
        return foodDiscountCodeByPercent;
    }

    public void setFoodDiscountCodeByPercent(String foodDiscountCodeByPercent) {
        this.foodDiscountCodeByPercent = foodDiscountCodeByPercent;
    }
}
