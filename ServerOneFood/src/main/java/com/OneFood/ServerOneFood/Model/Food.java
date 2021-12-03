package com.OneFood.ServerOneFood.Model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Table(name = "food")
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFood;
    @Column(name = "idStore",nullable = false)
    private long idStore;
    @Column(name = "idTypeOfFood",nullable = false)
    private long idTypeOfFood;
    @Column(nullable = false)
    private String foodName;
    @Column(nullable = false)
    private String foodImage;
    @Column(nullable = false)
    private String foodPrice;

    private String foodDescribe;


    @OneToMany(targetEntity = FoodOption.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "idFood",referencedColumnName = "idFood") // cot idFood ở bảng Food Option sẽ tham chiếu tới idFood cửa bảng Food
    private List<FoodOption> foodOptions;

    @OneToMany(targetEntity = FoodDiscountCode.class)
    @JoinColumn(name = "idFood", referencedColumnName = "idFood")
    private List<FoodDiscountCode> foodDiscountCodes;

    @OneToMany(targetEntity = Cart.class)
    @JoinColumn(name = "idFood", referencedColumnName = "idFood")
    private List<Cart> carts;

    @OneToMany(targetEntity = FoodReviews.class)
    @JoinColumn(name = "idFood", referencedColumnName = "idFood")
    private List<FoodReviews> foodReviews;



    public Food(long idStore, long idTypeOfFood, String foodName, String foodImage, String foodPrice, String foodDescribe, List<FoodOption> foodOptions, List<FoodDiscountCode> foodDiscountCodes, List<Cart> carts, List<FoodReviews> foodReviews) {
        this.idStore = idStore;
        this.idTypeOfFood = idTypeOfFood;
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.foodPrice = foodPrice;
        this.foodDescribe = foodDescribe;
        this.foodOptions = foodOptions;
        this.foodDiscountCodes = foodDiscountCodes;
        this.carts = carts;
        this.foodReviews = foodReviews;
    }

    public Food() {
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

    public long getIdTypeOfFood() {
        return idTypeOfFood;
    }

    public void setIdTypeOfFood(long idTypeOfFood) {
        this.idTypeOfFood = idTypeOfFood;
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

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<FoodReviews> getFoodReviews() {
        return foodReviews;
    }

    public void setFoodReviews(List<FoodReviews> foodReviews) {
        this.foodReviews = foodReviews;
    }
}
