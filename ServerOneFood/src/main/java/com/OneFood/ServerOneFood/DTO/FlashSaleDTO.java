package com.OneFood.ServerOneFood.DTO;

import com.OneFood.ServerOneFood.model.FlashSale;
import com.OneFood.ServerOneFood.model.Food;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

public class FlashSaleDTO {
    private long idFlashSale;
    private String flashSaleLevel;
    private List<FoodDTO> foods;

    public FlashSaleDTO(long idFlashSale, String flashSaleLevel, List<FoodDTO> foods) {
        this.idFlashSale = idFlashSale;
        this.flashSaleLevel = flashSaleLevel;
        this.foods = foods;
    }

    public FlashSaleDTO(FlashSale flashSale, List<FoodDTO> foods) {
        this.idFlashSale = flashSale.getIdFlashSale();
        this.flashSaleLevel = flashSale.getFlashSaleLevel();
        this.foods = foods;
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

    public List<FoodDTO> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodDTO> foods) {
        this.foods = foods;
    }
}
