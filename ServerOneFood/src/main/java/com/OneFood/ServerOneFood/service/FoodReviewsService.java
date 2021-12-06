package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.model.FoodReviews;
import com.OneFood.ServerOneFood.reponsitory.FoodReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodReviewsService {
    @Autowired
    private final FoodReviewsRepository  foodReviewsRepository;

    public FoodReviewsService(FoodReviewsRepository foodReviewsRepository) {
        this.foodReviewsRepository = foodReviewsRepository;
    }


    public ResponseEntity<ResponseObject> getAllFoodReviews(){
        List<FoodReviews> foodReviews =  foodReviewsRepository.findAll();
        if(foodReviews.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty food review list ", foodReviews));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find all successful food reviews ", foodReviews));

    }

    public ResponseEntity<ResponseObject> addNewFoodReviews(FoodReviews foodReviews){
        FoodReviews savedFoodReviews = foodReviewsRepository.save(foodReviews);
        if(savedFoodReviews == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"New FoodReviews create failed ",savedFoodReviews));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New FoodReviews successfully created ",savedFoodReviews));

    }

    public ResponseEntity<ResponseObject> updateFoodReviewsById(Long id, FoodReviews newFoodReviews)  {
        FoodReviews foodReviews = foodReviewsRepository.findById(id).orElse(null);
        if(foodReviews==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find food reviews with id "+id,null));
        foodReviews.setFoodReviewsStar(newFoodReviews.getFoodReviewsStar());
        foodReviews.setFoodReviewsImage(newFoodReviews.getFoodReviewsImage());
        foodReviews.setFoodReviewsStatus(newFoodReviews.getFoodReviewsStatus());
        foodReviews.setFoodReviewsTime(newFoodReviews.getFoodReviewsTime());
        foodReviews.setFoodReviewsContent(newFoodReviews.getFoodReviewsContent());


        FoodReviews updatedFoodReviews = foodReviewsRepository.save(foodReviews);
        if(updatedFoodReviews == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"Food views update failed ",updatedFoodReviews));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Food views successfully updated ",updatedFoodReviews));

    }

    public ResponseEntity<ResponseObject> getFoodReviewsById(Long id) {
        FoodReviews foodReviews = foodReviewsRepository.findById(id).orElse(null);
        if(foodReviews==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find food reviews with id "+id,null));

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find successful food reviews with id "+id,foodReviews));
    }

    public ResponseEntity<ResponseObject> deleteFoodReviewsById(Long id) {
        FoodReviews foodReviews = foodReviewsRepository.findById(id).orElse(null);
        if(foodReviews==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find food review with id "+id,null));
        foodReviewsRepository.delete(foodReviews);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful food review with id "+id,foodReviews));
    }
}
