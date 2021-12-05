package com.OneFood.ServerOneFood.DTO;

import com.OneFood.ServerOneFood.Model.*;

import javax.persistence.*;
import java.util.List;

public class FoodDTO {
    private long idFood;
    private long idStore;
    private long idTypeOfFood;
    private String foodName;
    private String foodImage;
    private String foodPrice;
    private String foodDescribe;
    private List<FoodOption> foodOptions;
    private List<FoodDiscountCode> foodDiscountCodes;
    private List<FoodReviews> foodReviews;

    public FoodDTO(Food food) {
        this.idFood = food.getIdFood();
        this.idStore = food.getIdStore();
        this.idTypeOfFood = food.getIdTypeOfFood();
        this.foodName = food.getFoodName();
        this.foodImage = food.getFoodImage();
        this.foodPrice = food.getFoodPrice();
        this.foodDescribe = food.getFoodDescribe();
        this.foodOptions = food.getFoodOptions();
        this.foodDiscountCodes = food.getFoodDiscountCodes();
        this.foodReviews = food.getFoodReviews();
    }

    public FoodDTO() {
    }

    public long getIdFood() {
        return idFood;
    }

    public void setIdFood(long idFood) {
        this.idFood = idFood;
    }

    public long getIdStore() {
        return idStore;
    }

    public void setIdStore(long idStore) {
        this.idStore = idStore;
    }

    public long getIdTypeOfFood() {
        return idTypeOfFood;
    }

    public void setIdTypeOfFood(long idTypeOfFood) {
        this.idTypeOfFood = idTypeOfFood;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodDescribe() {
        return foodDescribe;
    }

    public void setFoodDescribe(String foodDescribe) {
        this.foodDescribe = foodDescribe;
    }

    public List<FoodOption> getFoodOptions() {
        return foodOptions;
    }

    public void setFoodOptions(List<FoodOption> foodOptions) {
        this.foodOptions = foodOptions;
    }

    public List<FoodDiscountCode> getFoodDiscountCodes() {
        return foodDiscountCodes;
    }

    public void setFoodDiscountCodes(List<FoodDiscountCode> foodDiscountCodes) {
        this.foodDiscountCodes = foodDiscountCodes;
    }

    public List<FoodReviews> getFoodReviews() {
        return foodReviews;
    }

    public void setFoodReviews(List<FoodReviews> foodReviews) {
        this.foodReviews = foodReviews;
    }
}
