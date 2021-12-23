package com.OneFood.ServerOneFood.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class FlashSale {
    @Id
    @GeneratedValue
    private long idFlashSale;
    private String flashSaleLevel;

    @OneToMany(targetEntity = Food.class)
    @JoinColumn(name = "idFlashSale", referencedColumnName = "idFlashSale")
    private List<Food> foods;

    public FlashSale(long idFlashSale, String flashSaleLevel, List<Food> foods) {
        this.idFlashSale = idFlashSale;
        this.flashSaleLevel = flashSaleLevel;
        this.foods = foods;
    }

    public FlashSale() {
    }

    public long getIdFlashSale() {
        return idFlashSale;
    }

    public void setIdFlashSale(long idFlashSale) {
        this.idFlashSale = idFlashSale;
    }

    public String getFlashSaleLevel() {
        return flashSaleLevel;
    }

    public void setFlashSaleLevel(String flashSaleLevel) {
        this.flashSaleLevel = flashSaleLevel;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
