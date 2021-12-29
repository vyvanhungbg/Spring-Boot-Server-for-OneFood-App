package com.OneFood.ServerOneFood.DTO;

import com.OneFood.ServerOneFood.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class ItemInCartDTO extends FoodDTO{

    private int cartNumberOfFood;
    private boolean status;

    public ItemInCartDTO(Food food, Store store, int cartNumberOfFood, boolean status) {
        super(food, store);
        this.cartNumberOfFood = cartNumberOfFood;
        this.status = status;
    }

    public ItemInCartDTO() {
    }


    public int getCartNumberOfFood() {
        return cartNumberOfFood;
    }

    public void setCartNumberOfFood(int cartNumberOfFood) {
        this.cartNumberOfFood = cartNumberOfFood;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
