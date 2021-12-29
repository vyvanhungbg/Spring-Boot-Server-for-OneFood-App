package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.DTO.FoodDTO;
import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.Food;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.model.TypeOfFood;
import com.OneFood.ServerOneFood.reponsitory.FoodRepository;
import com.OneFood.ServerOneFood.reponsitory.TypeOfFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final TypeOfFoodRepository typeOfFoodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository, TypeOfFoodRepository typeOfFoodRepository) {
        this.foodRepository = foodRepository;

        this.typeOfFoodRepository = typeOfFoodRepository;
    }

    public ResponseEntity<ResponseObject> getAllFood(boolean sorted,int limit, float star){
        List<FoodDTO> foods =  foodRepository.getAllFoodDTO();
        if(foods.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty food list ", foods));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find "+foods.size()+" food successful  ", foods));

    }



    public ResponseEntity<ResponseObject> getFoodById(Long id) throws ErrorNotFoundException {
        FoodDTO foodDTO = foodRepository.getFoodDTOByID(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find food with id "+id));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Successful search food with id "+id,foodDTO));
    }

    public ResponseEntity<ResponseObject> addNewFood(Food food) throws ErrorExecutionFailedException {
        Food savedFood = foodRepository.save(food);
        if(savedFood == null)
            throw new ErrorExecutionFailedException("New  food save failed !");
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New  food save successful ! "+savedFood.getIdFood(), savedFood));
    }

    public ResponseEntity<ResponseObject> updateFoodById(Long id, Food newFood) throws ErrorNotFoundException {
        Food food = foodRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find food with id "+id));
        food.setFoodName(newFood.getFoodName());
        food.setFoodImage(newFood.getFoodImage());
        food.setFoodDescribe(newFood.getFoodDescribe());
        food.setFoodPrice(newFood.getFoodPrice());
        Food updatedFood = foodRepository.save(food);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Update successful food with id "+id,updatedFood));

    }



    public ResponseEntity<ResponseObject> deleteFoodById(Long id) throws ErrorNotFoundException, ErrorExecutionFailedException {
        Food food = foodRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find food with id "+id));
        foodRepository.delete(food);
        if(foodRepository.existsById(id))
            throw new ErrorExecutionFailedException("Delete food failed with id "+id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful food with id "+id,food));

    }

}
