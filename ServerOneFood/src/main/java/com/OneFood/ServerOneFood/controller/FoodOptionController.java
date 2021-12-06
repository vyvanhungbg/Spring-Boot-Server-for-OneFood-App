package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.model.FoodOption;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.service.FoodOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/food-option")
public class FoodOptionController {
    @Autowired
    private final FoodOptionService foodOptionService;

    public FoodOptionController(FoodOptionService foodOptionService) {
        this.foodOptionService = foodOptionService;
    }


    @GetMapping("")
    ResponseEntity<ResponseObject> getAllFoodOption(){
        return foodOptionService.getAllFoodOption();
    }
    @PostMapping("")
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@RequestBody FoodOption foodOption){
        return foodOptionService.addNewFoodOption(foodOption);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getFoodOptionsById(@PathVariable Long id){
        return foodOptionService.getFoodOptionById(id);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateFoodOptionsById(@PathVariable(value = "id") Long id, @RequestBody FoodOption newFoodOption)  {
        return foodOptionService.updateFoodOptionById(id,newFoodOption);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteFoodOptionsById(@PathVariable(value = "id") Long id){
        return foodOptionService.deleteFoodOptionById(id);
    }
}
