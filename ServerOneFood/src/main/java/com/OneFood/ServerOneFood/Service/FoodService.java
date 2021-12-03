package com.OneFood.ServerOneFood.Service;

import com.OneFood.ServerOneFood.Exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.Model.Food;
import com.OneFood.ServerOneFood.Model.ResponseObject;
import com.OneFood.ServerOneFood.Reponsitory.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public ResponseEntity<ResponseObject> getAllFood(){
        List<Food> foods =  foodRepository.findAll();
        if(foods.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty food list ", foods));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find all successful food ", foods));

    }

    public ResponseEntity<ResponseObject> getFoodById(Long id) {
        Optional<Food> food = foodRepository.findById(id);
        if(food.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Successful search food with id "+id,food));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find food with id "+id,null));
    }

    public ResponseEntity<ResponseObject> addNewFood(Food food){
        Food savedFood = foodRepository.save(food);
        if(savedFood == null)
            return  ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"New  food save failed !", null));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New  food save successful ! ", savedFood));

    }

    public ResponseEntity<ResponseObject> updateFoodById(Long id, Food newFood) throws ErrorNotFoundException {
        Food food = foodRepository.findById(id).orElse(null);
        if(food==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find food with id "+id,null));
        food.setFoodName(newFood.getFoodName());
        food.setFoodImage(newFood.getFoodImage());
        food.setFoodDescribe(newFood.getFoodDescribe());
        food.setFoodPrice(newFood.getFoodPrice());
        Food updatedFood = foodRepository.save(food);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Update successful food with id "+id,updatedFood));

    }



    public ResponseEntity<ResponseObject> deleteFoodById(Long id) {
        Food food = foodRepository.findById(id).orElse(null);
        if(food==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find food with id "+id,null));
        foodRepository.delete(food);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful food with id "+id,food));

    }


}
