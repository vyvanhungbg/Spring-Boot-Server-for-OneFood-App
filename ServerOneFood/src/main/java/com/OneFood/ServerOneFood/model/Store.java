package com.OneFood.ServerOneFood.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

// Cửa hàng cung cấp thực phẩm
@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idStore;
    @NotNull(message = "This field can not be null")
    private String storeName;
    @NotNull(message = "This field can not be null")
    private String storeImage;
    @NotNull(message = "This field can not be null")
    private String storeAddress;

    private String storeStart;
    @NotNull(message = "This field can not be null")
    private String storeLongitude;
    @NotNull(message = "This field can not be null")
    private String storeLatitude;
    @NotNull(message = "This field can not be null")
    private String storeWorkTime;
    private String storeSlogan;

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

    public Store(String storeName, String storeImage, String storeAddress, String storeStart, String storeLongitude, String storeLatitude, String storeWorkTime, String storeSlogan, List<Food> foods, List<FoodDiscountCode> foodDiscountCodes, List<Bill> bills) {
        this.storeName = storeName;
        this.storeImage = storeImage;
        this.storeAddress = storeAddress;
        this.storeStart = storeStart;
        this.storeLongitude = storeLongitude;
        this.storeLatitude = storeLatitude;
        this.storeWorkTime = storeWorkTime;
        this.storeSlogan = storeSlogan;
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

    public List<Food> getFoods() {
        return foods;
    }

    public String getStoreSlogan() {
        return storeSlogan;
    }

    public void setStoreSlogan(String storeSlogan) {
        this.storeSlogan = storeSlogan;
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

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}
