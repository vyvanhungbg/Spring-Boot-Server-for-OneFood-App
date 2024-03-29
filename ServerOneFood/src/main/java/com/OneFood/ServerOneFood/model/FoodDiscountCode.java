package com.OneFood.ServerOneFood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "food_discount_code")
public class FoodDiscountCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFoodDiscountCode;
    private long idStore;
    @NotNull(message = "This field can not be null")
    private long idTypeOfDiscountCode;
    private int foodDiscountCodeIsDestroy;
    int foodDiscountCodeStatus;
    @NotNull(message = "This field can not be null")
    private String foodDiscountCodeDescribe;
    private String foodDiscountCodeImage;
    @NotNull(message = "This field can not be null")
    private String foodDiscountEndTime;
    @NotNull(message = "This field can not be null")
    private String foodDiscountStartTime;
    private String foodDiscountCodeByMoney;
    private String foodDiscountCodeByPercent;
    @JsonIgnore
    private boolean forAllUser = true; // mã này có phải cho mọi người hay không hay là mã cho 1 số ít người bằng nhập code
    private boolean isSave = false;  // xem đã lưu mã này hay chưa
    @JsonIgnore
    private String code;

    @JsonIgnore
    @ManyToMany(mappedBy = "foodDiscountCodes", fetch = FetchType.LAZY) //
    private List<Food> foods = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "foodDiscountCode", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<UserDiscount> userDiscounts;

    public Set<UserDiscount> getUserDiscounts() {
        return userDiscounts;
    }

    public void setUserDiscounts(Set<UserDiscount> userDiscounts) {
        this.userDiscounts = userDiscounts;
    }

    public FoodDiscountCode() {
        this.forAllUser = true;
    }



    public FoodDiscountCode( long idStore,  long idTypeOfDiscountCode, int foodDiscountCodeIsDestroy, int foodDiscountCodeStatus, String foodDiscountCodeDescribe, String foodDiscountCodeImage, String foodDiscountEndTime, String foodDiscountStartTime, String foodDiscountCodeByMoney, String foodDiscountCodeByPercent) {
        this.idStore = idStore;
        this.idTypeOfDiscountCode = idTypeOfDiscountCode;
        this.foodDiscountCodeIsDestroy = foodDiscountCodeIsDestroy;
        this.foodDiscountCodeStatus = foodDiscountCodeStatus;
        this.foodDiscountCodeDescribe = foodDiscountCodeDescribe;
        this.foodDiscountCodeImage = foodDiscountCodeImage;
        this.foodDiscountEndTime = foodDiscountEndTime;
        this.foodDiscountStartTime = foodDiscountStartTime;
        this.foodDiscountCodeByMoney = foodDiscountCodeByMoney;
        this.foodDiscountCodeByPercent = foodDiscountCodeByPercent;
        this.forAllUser = true;
    }

    public void addFood(Food food) {
        foods.add(food);
        food.getFoodDiscountCodes().add(this);
    }

    public void removeFood(Food food) {
        foods.remove(food);
        food.getFoodDiscountCodes().remove(this);
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
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

    public boolean isForAllUser() {
        return forAllUser;
    }

    public void setForAllUser(boolean forAllUser) {
        this.forAllUser = forAllUser;
    }

    public boolean isSave() {
        return isSave;
    }

    public void setSave(boolean save) {
        isSave = save;
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
