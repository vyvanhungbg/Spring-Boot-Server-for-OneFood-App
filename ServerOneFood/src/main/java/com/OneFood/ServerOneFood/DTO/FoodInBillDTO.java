package com.OneFood.ServerOneFood.DTO;

import com.OneFood.ServerOneFood.model.Food;
import com.OneFood.ServerOneFood.model.OrderFoodDetails;
import com.OneFood.ServerOneFood.model.Store;

import javax.validation.constraints.NotNull;

public class FoodInBillDTO extends FoodDTO {

    private int numberOfFood;
    private String foodOldPrice;

    public FoodInBillDTO(FoodDTO food, int numberOfFood, String foodOldPrice) {
        super(food);
        this.numberOfFood = numberOfFood;
        this.foodOldPrice = foodOldPrice;
    }

    public int getNumberOfFood() {
        return numberOfFood;
    }

    public void setNumberOfFood(int numberOfFood) {
        this.numberOfFood = numberOfFood;
    }

    public String getFoodOldPrice() {
        return foodOldPrice;
    }

    public void setFoodOldPrice(String foodOldPrice) {
        this.foodOldPrice = foodOldPrice;
    }
}
