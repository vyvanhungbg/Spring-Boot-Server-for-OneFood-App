package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.Food;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.service.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("one-app/v1/food")
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }
    @GetMapping("")
    ResponseEntity<ResponseObject> getAllFood(){
        return foodService.getAllFood();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getFoodById(@PathVariable Long id) throws ErrorNotFoundException {
        return foodService.getFoodById(id);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> addNewFood(@Valid @RequestBody Food food) throws ErrorExecutionFailedException {
        return foodService.addNewFood(food);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> updateFood(@PathVariable(value = "id") Long id, @Valid @RequestBody Food newFood) throws ErrorNotFoundException {
        return foodService.updateFoodById(id,newFood);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> updateFood(@PathVariable(value = "id") Long id) throws ErrorExecutionFailedException, ErrorNotFoundException {
        return foodService.deleteFoodById(id);
    }
}
