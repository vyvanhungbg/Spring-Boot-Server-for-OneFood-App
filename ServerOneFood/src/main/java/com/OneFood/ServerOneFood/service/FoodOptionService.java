package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.model.FoodOption;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.reponsitory.FoodOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodOptionService {

    @Autowired
    private final FoodOptionRepository foodOptionRepository;

    public FoodOptionService(FoodOptionRepository foodOptionRepository) {
        this.foodOptionRepository = foodOptionRepository;
    }


    public ResponseEntity<ResponseObject> getAllFoodOption(){
        List<FoodOption> foodOptions =  foodOptionRepository.findAll();
        if(foodOptions.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty foodOption list ", foodOptions));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find all successful foodOption ", foodOptions));

    }

    public ResponseEntity<ResponseObject> addNewFoodOption(FoodOption newFoodOption){
        FoodOption foodOption = foodOptionRepository.save(newFoodOption);
        if(foodOption == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"New foodOption create failed ",foodOption));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New foodOption successfully created ",foodOption));

    }

    public ResponseEntity<ResponseObject> updateFoodOptionById(Long id, FoodOption newFoodOption)  {
        FoodOption foodOption = foodOptionRepository.findById(id).orElse(null);
        if(foodOption==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find foodOption with id "+id,null));
        foodOption.setDescriptionFoodOption(newFoodOption.getDescriptionFoodOption());
        foodOption.setPriceOfFoodOption(newFoodOption.getPriceOfFoodOption());
        foodOption.setSizeOfFoodOption(newFoodOption.getSizeOfFoodOption());

        FoodOption updatedFoodOption = foodOptionRepository.save(foodOption);
        if(updatedFoodOption == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"FoodOption update failed ",updatedFoodOption));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"FoodOption successfully updated ",updatedFoodOption));

    }

    public ResponseEntity<ResponseObject> getFoodOptionById(Long id) {
        FoodOption foodOption = foodOptionRepository.findById(id).orElse(null);
        if(foodOption==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find foodOption with id "+id,null));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find successful foodOption with id "+id,foodOption));
    }

    public ResponseEntity<ResponseObject> deleteFoodOptionById(Long id) {
        FoodOption foodOption = foodOptionRepository.findById(id).orElse(null);
        if(foodOption==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find foodOption with id "+id,null));
        foodOptionRepository.delete(foodOption);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful foodOption with id "+id,foodOption));
    }
}
