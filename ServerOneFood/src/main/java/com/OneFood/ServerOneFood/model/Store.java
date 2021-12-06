package com.OneFood.ServerOneFood.model;

import javax.persistence.*;
import java.util.List;

// Cửa hàng cung cấp thực phẩm
@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idStore;
    private String storeName;
    private String storeImage;
    private String storeAddress;
    private String storeLongitude;
    private String storeLatitude;
    private String storeWorkTime;

    @OneToMany(targetEntity = Food.class)
    @JoinColumn(name = "idStore",referencedColumnName = "idStore" )
    private List<Food> foods;

    @OneToMany(targetEntity = FoodDiscountCode.class)
    @JoinColumn(name = "idStore", referencedColumnName = "idStore")
    private List<FoodDiscountCode> foodDiscountCodes;

    @OneToMany(targetEntity = Bill.class)
    @JoinColumn(name = "idStore", referencedColumnName = "idStore")
    private List<Bill> bills;
    public Store() {
    }

    public Store(String storeName, String storeImage, String storeAddress, String storeLongitude, String storeLatitude, String storeWorkTime, List<Food> foods, List<FoodDiscountCode> foodDiscountCodes, List<Bill> bills) {
        this.storeName = storeName;
        this.storeImage = storeImage;
        this.storeAddress = storeAddress;
        this.storeLongitude = storeLongitude;
        this.storeLatitude = storeLatitude;
        this.storeWorkTime = storeWorkTime;
        this.foods = foods;
        this.foodDiscountCodes = foodDiscountCodes;
        this.bills = bills;
    }

    public long getIdStore() {
        return idStore;
    }

    public void setIdStore(long idStore) {
        this.idStore = idStore;
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

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public List<FoodDiscountCode> getFoodDiscountCodes() {
        return foodDiscountCodes;
    }

    public void setFoodDiscountCodes(List<FoodDiscountCode> foodDiscountCodes) {
        this.foodDiscountCodes = foodDiscountCodes;
    }
}
