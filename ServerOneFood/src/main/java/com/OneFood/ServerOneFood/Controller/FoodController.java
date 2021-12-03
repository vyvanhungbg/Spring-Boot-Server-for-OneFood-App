package com.OneFood.ServerOneFood.Controller;

import com.OneFood.ServerOneFood.Exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.Model.Food;
import com.OneFood.ServerOneFood.Model.ResponseObject;
import com.OneFood.ServerOneFood.Service.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/food")
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
    public ResponseEntity<ResponseObject> getFoodById(@PathVariable Long id){
        return foodService.getFoodById(id);
    }

    @PostMapping("")
    public ResponseEntity<ResponseObject> addNewFood(@RequestBody Food food){
        return foodService.addNewFood(food);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateFood(@PathVariable(value = "id") Long id, @RequestBody Food newFood) throws ErrorNotFoundException {
        return foodService.updateFoodById(id,newFood);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> updateFood(@PathVariable(value = "id") Long id){
        return foodService.deleteFoodById(id);
    }
}
