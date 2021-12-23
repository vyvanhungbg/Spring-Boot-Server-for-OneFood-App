package com.OneFood.ServerOneFood.DTO;

import com.OneFood.ServerOneFood.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class FoodDTO {
    private long idFood;
    private long idStore;
    private long idTypeOfFood;
    private String foodName;
    private String foodImage;
    private String foodPrice;
    private String foodDescribe;
    private String foodTag;
    private String foodStar;

    private List<FoodOption> foodOptions;
    @JsonIgnore
    private List<FoodDiscountCode> foodDiscountCodes;
    @JsonIgnore
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
        this.foodTag = food.getFoodTag();
        this.foodStar = food.getFoodStar();
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

    public String getFoodTag() {
        return foodTag;
    }

    public void setFoodTag(String foodTag) {
        this.foodTag = foodTag;
    }

    public String getFoodStar() {
        return foodStar;
    }

    public void setFoodStar(String foodStar) {
        this.foodStar = foodStar;
    }

    public List<FoodReviews> getFoodReviews() {
        return foodReviews;
    }

    public void setFoodReviews(List<FoodReviews> foodReviews) {
        this.foodReviews = foodReviews;
    }
}
