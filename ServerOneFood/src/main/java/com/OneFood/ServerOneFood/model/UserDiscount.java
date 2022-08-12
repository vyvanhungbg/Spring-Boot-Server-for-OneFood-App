package com.OneFood.ServerOneFood.model;

import javax.persistence.*;

@Entity
@Table
public class UserDiscount {

    @EmbeddedId
    private UserDiscountKey id = new UserDiscountKey();


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUser")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idFoodDiscountCode")
    FoodDiscountCode foodDiscountCode;

    boolean isUsed;

    public UserDiscount() {
    }

    public UserDiscountKey getId() {
        return id;
    }

    public void setId(UserDiscountKey id) {
        this.id = id;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public UserDiscount(User user, FoodDiscountCode foodDiscountCode) {
        this.user = user;
        this.foodDiscountCode = foodDiscountCode;
        this.isUsed = false;
    }

    public UserDiscount( User user, FoodDiscountCode foodDiscountCode, boolean isUsed) {
        this.id = new UserDiscountKey(user.getIdUser(), foodDiscountCode.getIdFoodDiscountCode());
        this.user = user;
        this.foodDiscountCode = foodDiscountCode;
        this.isUsed = isUsed;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed() {
        isUsed = true;
    }

    public void setNotUsed() {
        isUsed = false;
    }

    public FoodDiscountCode getFoodDiscountCode() {
        return foodDiscountCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFoodDiscountCode(FoodDiscountCode foodDiscountCode) {
        this.foodDiscountCode = foodDiscountCode;
    }


}
