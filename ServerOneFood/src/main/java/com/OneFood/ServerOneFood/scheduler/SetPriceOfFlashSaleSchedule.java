package com.OneFood.ServerOneFood.scheduler;


import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.FlashSale;
import com.OneFood.ServerOneFood.model.Food;
import com.OneFood.ServerOneFood.service.FlashSaleService;
import com.OneFood.ServerOneFood.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@Transactional
public class SetPriceOfFlashSaleSchedule {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss");
    private static final Logger LOGGER = LoggerFactory.getLogger(SetPriceOfFlashSaleSchedule.class);
    @Autowired
    private final FoodService foodService;
    @Autowired
    private final FlashSaleService flashSaleService;

    public SetPriceOfFlashSaleSchedule(FoodService foodService, FlashSaleService flashSaleService) {
        this.foodService = foodService;
        this.flashSaleService = flashSaleService;
    }
    //@Scheduled( fixedDelay = 10*1000)
    public void setPriceWhileDiscountStar(){
        LOGGER.info("Start set price flash sale");
        List<FlashSale> flashSales = flashSaleService.getAllFlashSaleNormal();
        flashSales.stream().forEach(flashSale -> {
            if(!flashSale.isFlashSale()){ // kiem tra chua flash sale thì mới set giá
                List<Food> foods = flashSale.getFoods();
                foods.stream().forEach(food -> {
                    try {
                        String newPrice = Double.parseDouble(food.getFoodPrice()) * (1-Double.parseDouble(flashSale.getFlashSaleLevel())/100) +"";
                        foodService.updatePriceFoodWhileFlashSaleById(food.getIdFood(),newPrice);
                    } catch (ErrorNotFoundException e) {
                        e.printStackTrace();
                    }
                });
                try {
                    flashSaleService.updateIsFlashSale(flashSale.getIdFlashSale(),true);
                } catch (ErrorNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
   //
    //@Scheduled(initialDelay = 10*1000, fixedDelay = 10*10000000)
    public void setPriceWhileDiscountEnd(){
        LOGGER.info("End set price flash sale");
        List<FlashSale> flashSales = flashSaleService.getAllFlashSaleNormal();
        flashSales.stream().forEach(flashSale -> {
            if(flashSale.isFlashSale()){ // kiem tra  flash sale thì mới set giá
                List<Food> foods = flashSale.getFoods();
                foods.stream().forEach(food -> {
                    try {
                        String oldPrice = Double.parseDouble(food.getFoodPrice()) / (1-Double.parseDouble(flashSale.getFlashSaleLevel())/100) +"";
                        foodService.updatePriceFoodWhileFlashSaleById(food.getIdFood(),oldPrice);
                    } catch (ErrorNotFoundException e) {
                        e.printStackTrace();
                    }
                });
                try {
                    flashSaleService.updateIsFlashSale(flashSale.getIdFlashSale(),false);
                } catch (ErrorNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        LOGGER.info("End set price flash sale finish");
    }


   // @Scheduled(initialDelay = 5*1000, fixedDelay = 5*1000)
    public void sample(){
        //LOGGER.info("Hello");
        Date now = new Date();
        System.out.println("Now is "+df.format(now));
    }
}
