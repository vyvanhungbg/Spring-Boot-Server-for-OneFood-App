package com.OneFood.ServerOneFood.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


// Class này sẽ là thực thể cho các loại size M, L ,S or các mức giá đồ ăn
@Entity
@Table(name = "food_option")
public class FoodOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFoodOption;
    private long idFood;
    @Column(name = "sizeOfFoodOption")
    @NotNull(message = "This field can not be null")
    private int sizeOfFoodOption;
    @Column(name = "descriptionFoodOption")
    @NotNull(message = "This field can not be null")
    private String descriptionFoodOption;
    @Column(name = "priceOfFoodOption")
    @NotNull(message = "This field can not be null")
    private String priceOfFoodOption;


    public FoodOption() {
    }

    public FoodOption(long idFood, int sizeOfFoodOption, String descriptionFoodOption, String priceOfFoodOption) {
        this.idFood = idFood;
        this.sizeOfFoodOption = sizeOfFoodOption;
        this.descriptionFoodOption = descriptionFoodOption;
        this.priceOfFoodOption = priceOfFoodOption;
    }

    public long getIdFood() {
        return idFood;
    }

    public void setIdFood(long idFood) {
        this.idFood = idFood;
    }

    public long getIdFoodOption() {
        return idFoodOption;
    }

    public void setIdFoodOption(long idFoodOption) {
        this.idFoodOption = idFoodOption;
    }

    public int getSizeOfFoodOption() {
        return sizeOfFoodOption;
    }

    public void setSizeOfFoodOption(int sizeOfFoodOption) {
        this.sizeOfFoodOption = sizeOfFoodOption;
    }

    public String getDescriptionFoodOption() {
        return descriptionFoodOption;
    }

    public void setDescriptionFoodOption(String descriptionFoodOption) {
        this.descriptionFoodOption = descriptionFoodOption;
    }

    public String getPriceOfFoodOption() {
        return priceOfFoodOption;
    }

    public void setPriceOfFoodOption(String priceOfFoodOption) {
        this.priceOfFoodOption = priceOfFoodOption;
    }


}

