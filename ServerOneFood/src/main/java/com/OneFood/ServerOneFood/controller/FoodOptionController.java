package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.FoodOption;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.service.FoodOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("one-app/v1/food-option")
@PreAuthorize("isAuthenticated()")
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




    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getFoodOptionsById(@PathVariable Long id) throws ErrorNotFoundException {
        return foodOptionService.getFoodOptionById(id);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@Valid @RequestBody FoodOption foodOption) throws ErrorExecutionFailedException {
        return foodOptionService.addNewFoodOption(foodOption);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> updateFoodOptionsById(@PathVariable(value = "id") Long id, @Valid @RequestBody FoodOption newFoodOption) throws ErrorExecutionFailedException, ErrorNotFoundException {
        return foodOptionService.updateFoodOptionById(id,newFoodOption);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> deleteFoodOptionsById(@PathVariable(value = "id") Long id) throws ErrorNotFoundException {
        return foodOptionService.deleteFoodOptionById(id);
    }
}
