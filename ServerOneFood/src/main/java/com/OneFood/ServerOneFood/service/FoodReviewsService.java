package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
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
    @Autowired
    private final MyService myService;

    public FoodReviewsService(FoodReviewsRepository foodReviewsRepository, MyService myService) {
        this.foodReviewsRepository = foodReviewsRepository;
        this.myService = myService;
    }


    public ResponseEntity<ResponseObject> getAllFoodReviews(){
        List<FoodReviews> foodReviews =  foodReviewsRepository.findAll();
        if(foodReviews.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty food review list ", foodReviews));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find "+foodReviews.size()+" food reviews ", foodReviews));

    }

    public ResponseEntity<ResponseObject> addNewFoodReviews(FoodReviews foodReviews) throws ErrorExecutionFailedException {
        if(foodReviewsRepository.existsById(foodReviews.getIdFoodReviews()))
            throw new ErrorExecutionFailedException("New food review create failed Because this item already exists");
        Long idUser = myService.getPrincipal();
        foodReviews.setIdUser(idUser);
        FoodReviews savedFoodReviews = foodReviewsRepository.save(foodReviews);
        if(savedFoodReviews == null)
            throw new ErrorExecutionFailedException("New FoodReviews create failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New FoodReviews successfully created ",savedFoodReviews));

    }

    public ResponseEntity<ResponseObject> updateFoodReviewsById(Long id, FoodReviews newFoodReviews) throws ErrorExecutionFailedException, ErrorNotFoundException {
        FoodReviews foodReviews = foodReviewsRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find food reviews with id "+id));

        foodReviews.setFoodReviewsStar(newFoodReviews.getFoodReviewsStar());
        foodReviews.setFoodReviewsImage(newFoodReviews.getFoodReviewsImage());
        foodReviews.setFoodReviewsStatus(newFoodReviews.getFoodReviewsStatus());
        foodReviews.setFoodReviewsTime(newFoodReviews.getFoodReviewsTime());
        foodReviews.setFoodReviewsContent(newFoodReviews.getFoodReviewsContent());

        FoodReviews updatedFoodReviews = foodReviewsRepository.save(foodReviews);
        if(updatedFoodReviews == null)
            throw new ErrorExecutionFailedException("Food views update failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Food views successfully updated ",updatedFoodReviews));

    }

    public ResponseEntity<ResponseObject> getFoodReviewsById(Long id) throws ErrorNotFoundException {
        FoodReviews foodReviews = foodReviewsRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find food reviews with id "+id));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find successful food reviews with id "+id,foodReviews));
    }

    public ResponseEntity<ResponseObject> deleteFoodReviewsById(Long id) throws ErrorNotFoundException {
        FoodReviews foodReviews = foodReviewsRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find food review with id "+id));
        foodReviewsRepository.delete(foodReviews);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful food review with id "+id,foodReviews));
    }
}
