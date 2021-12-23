package com.OneFood.ServerOneFood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Table(name = "food")
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFood;
    @NotNull(message = "This field can not be null") // nếu sau chủ store put thì cần xóa
    private long idStore;
    @NotNull(message = "This field can not be null")
    private long idTypeOfFood;

    private long idFlashSale;
    @NotNull(message = "This field can not be null")
    private String foodName;
    @NotNull(message = "This field can not be null")
    private String foodImage;
    @NotNull(message = "This field can not be null")
    private String foodPrice;

    private String foodDescribe;
    private String foodTag;
    private String foodStar;


    @OneToMany(targetEntity = FoodOption.class)
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


    public Food(long idStore, long idTypeOfFood, long idFlashSale, String foodName, String foodImage, String foodPrice, String foodDescribe, String foodTag, String foodStar, List<FoodOption> foodOptions, List<FoodDiscountCode> foodDiscountCodes, List<Cart> carts, List<FoodReviews> foodReviews) {
        this.idStore = idStore;
        this.idTypeOfFood = idTypeOfFood;
        this.idFlashSale = idFlashSale;
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.foodPrice = foodPrice;
        this.foodDescribe = foodDescribe;
        this.foodTag = foodTag;
        this.foodStar = foodStar;
        this.foodOptions = foodOptions;
        this.foodDiscountCodes = foodDiscountCodes;
        this.carts = carts;
        this.foodReviews = foodReviews;
    }

    public Food() {
    }

    public long getIdFlashSale() {
        return idFlashSale;
    }

    public void setIdFlashSale(long idFlashSale) {
        this.idFlashSale = idFlashSale;
    }

    public String getFoodStar() {
        return foodStar;
    }

    public void setFoodStar(String foodStar) {
        this.foodStar = foodStar;
    }

    public String getFoodTag() {
        return foodTag;
    }

    public void setFoodTag(String foodTag) {
        this.foodTag = foodTag;
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
