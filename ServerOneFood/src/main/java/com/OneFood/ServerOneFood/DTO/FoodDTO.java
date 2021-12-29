package com.OneFood.ServerOneFood.DTO;

import com.OneFood.ServerOneFood.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;

public class FoodDTO {
    protected long idFood;
    protected long idStore;
    protected long idTypeOfFood;
    protected String foodName;
    protected String foodImage;
    protected String foodPrice;
    protected String foodDescribe;
    protected String foodTag;
    protected String foodStar;


    // field of store
    protected String storeName;
    protected String storeImage;
    protected String storeAddress;
    protected String storeStart;
    protected String storeLongitude;
    protected String storeLatitude;
    protected String storeWorkTime;
    protected String storeSlogan;

    private List<FoodOption> foodOptions;
    //@JsonIgnore
    private List<FoodDiscountCode> foodDiscountCodes;
    //@JsonIgnore
    private List<FoodReviews> foodReviews;


    public FoodDTO(long idFood, long idStore, long idTypeOfFood, String foodName, String foodImage, String foodPrice, String foodDescribe, String foodTag, String foodStar, String storeName, String storeImage, String storeAddress, String storeStart, String storeLongitude, String storeLatitude, String storeWorkTime, String storeSlogan, List<FoodOption> foodOptions, List<FoodDiscountCode> foodDiscountCodes, List<FoodReviews> foodReviews) {
        this.idFood = idFood;
        this.idStore = idStore;
        this.idTypeOfFood = idTypeOfFood;
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.foodPrice = foodPrice;
        this.foodDescribe = foodDescribe;
        this.foodTag = foodTag;
        this.foodStar = foodStar;
        this.storeName = storeName;
        this.storeImage = storeImage;
        this.storeAddress = storeAddress;
        this.storeStart = storeStart;
        this.storeLongitude = storeLongitude;
        this.storeLatitude = storeLatitude;
        this.storeWorkTime = storeWorkTime;
        this.storeSlogan = storeSlogan;
        this.foodOptions = foodOptions;
        this.foodDiscountCodes = foodDiscountCodes;
        this.foodReviews = foodReviews;
    }






    public FoodDTO(long idFood, long idStore, long idTypeOfFood, String foodName, String foodImage, String foodPrice, String foodDescribe, String foodTag, String foodStar) {
        this.idFood = idFood;
        this.idStore = idStore;
        this.idTypeOfFood = idTypeOfFood;
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.foodPrice = foodPrice;
        this.foodDescribe = foodDescribe;
        this.foodTag = foodTag;
        this.foodStar = foodStar;
    }

    public FoodDTO(long idFood, long idStore, long idTypeOfFood, String foodName, String foodImage, String foodPrice, String foodDescribe, String foodTag, String foodStar, String storeName, String storeImage, String storeAddress, String storeStart, String storeLongitude, String storeLatitude, String storeWorkTime, String storeSlogan) {
        this.idFood = idFood;
        this.idStore = idStore;
        this.idTypeOfFood = idTypeOfFood;
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.foodPrice = foodPrice;
        this.foodDescribe = foodDescribe;
        this.foodTag = foodTag;
        this.foodStar = foodStar;
        this.storeName = storeName;
        this.storeImage = storeImage;
        this.storeAddress = storeAddress;
        this.storeStart = storeStart;
        this.storeLongitude = storeLongitude;
        this.storeLatitude = storeLatitude;
        this.storeWorkTime = storeWorkTime;
        this.storeSlogan = storeSlogan;
    }



    public FoodDTO(Food food, Store store) {
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

        this.storeName = store.getStoreName();
        this.storeImage = store.getStoreImage();
        this.storeAddress = store.getStoreAddress();
        this.storeStart = store.getStoreStart();
        this.storeLongitude = store.getStoreLongitude();
        this.storeLatitude = store.getStoreLatitude();
        this.storeWorkTime = store.getStoreWorkTime();
        this.storeSlogan = store.getStoreSlogan();
    }

    public FoodDTO(FoodDTO food) {
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

        this.storeName = food.getStoreName();
        this.storeImage = food.getStoreImage();
        this.storeAddress = food.getStoreAddress();
        this.storeStart = food.getStoreStart();
        this.storeLongitude = food.getStoreLongitude();
        this.storeLatitude = food.getStoreLatitude();
        this.storeWorkTime = food.getStoreWorkTime();
        this.storeSlogan = food.getStoreSlogan();

        this.foodOptions = food.foodOptions;
        this.foodDiscountCodes = food.foodDiscountCodes;
        this.foodReviews = food.foodReviews;
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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreStart() {
        return storeStart;
    }

    public void setStoreStart(String storeStart) {
        this.storeStart = storeStart;
    }

    public String getStoreLongitude() {
        return storeLongitude;
    }

    public void setStoreLongitude(String storeLongitude) {
        this.storeLongitude = storeLongitude;
    }

    public String getStoreLatitude() {
        return storeLatitude;
    }

    public void setStoreLatitude(String storeLatitude) {
        this.storeLatitude = storeLatitude;
    }

    public String getStoreWorkTime() {
        return storeWorkTime;
    }

    public void setStoreWorkTime(String storeWorkTime) {
        this.storeWorkTime = storeWorkTime;
    }

    public String getStoreSlogan() {
        return storeSlogan;
    }

    public void setStoreSlogan(String storeSlogan) {
        this.storeSlogan = storeSlogan;
    }
}
