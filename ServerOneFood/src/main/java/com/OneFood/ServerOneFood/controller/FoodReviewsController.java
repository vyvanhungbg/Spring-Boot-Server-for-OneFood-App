package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.FoodReviews;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.service.FoodReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("one-app/v1/food-reviews")

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
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@Valid @RequestBody FoodReviews user) throws ErrorExecutionFailedException {
        return foodReviewsService.addNewFoodReviews(user);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getFoodReviewsById(@PathVariable Long id) throws ErrorNotFoundException {
        return foodReviewsService.getFoodReviewsById(id);
    }



    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> updateFoodReviewsById(@PathVariable(value = "id") Long id, @Valid @RequestBody FoodReviews newFoodReviews) throws ErrorExecutionFailedException, ErrorNotFoundException {
        return foodReviewsService.updateFoodReviewsById(id,newFoodReviews);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> deleteFoodReviewsById(@PathVariable(value = "id") Long id) throws ErrorNotFoundException {
        return foodReviewsService.deleteFoodReviewsById(id);
    }
}
