package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.model.FoodReviews;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.service.FoodReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/food-reviews")
public class FoodReviewsController {

    @Autowired
    private final FoodReviewsService foodReviewsService;

    public FoodReviewsController(FoodReviewsService foodReviewsService) {
        this.foodReviewsService = foodReviewsService;
    }

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllFoodReview(){
        return foodReviewsService.getAllFoodReviews();
    }
    @PostMapping("")
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@RequestBody FoodReviews user){
        return foodReviewsService.addNewFoodReviews(user);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getFoodReviewsById(@PathVariable Long id){
        return foodReviewsService.getFoodReviewsById(id);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateFoodReviewsById(@PathVariable(value = "id") Long id, @RequestBody FoodReviews newFoodReviews)  {
        return foodReviewsService.updateFoodReviewsById(id,newFoodReviews);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteFoodReviewsById(@PathVariable(value = "id") Long id){
        return foodReviewsService.deleteFoodReviewsById(id);
    }
}
