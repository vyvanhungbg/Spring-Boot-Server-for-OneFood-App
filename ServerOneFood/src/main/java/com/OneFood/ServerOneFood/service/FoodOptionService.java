package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
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
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find "+foodOptions.size()+" foodOption ", foodOptions));

    }

    public ResponseEntity<ResponseObject> addNewFoodOption(FoodOption newFoodOption) throws ErrorExecutionFailedException {
        if(foodOptionRepository.existsById(newFoodOption.getIdFoodOption()))
            throw new ErrorExecutionFailedException("New food option create failed Because this item already exists");
        FoodOption foodOption = foodOptionRepository.save(newFoodOption);
        if(foodOption == null)
            throw  new ErrorExecutionFailedException("New foodOption create failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New foodOption successfully created ",foodOption));

    }

    public ResponseEntity<ResponseObject> updateFoodOptionById(Long id, FoodOption newFoodOption) throws ErrorNotFoundException, ErrorExecutionFailedException {
        FoodOption foodOption = foodOptionRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find foodOption with id "+id));

        foodOption.setDescriptionFoodOption(newFoodOption.getDescriptionFoodOption());
        foodOption.setPriceOfFoodOption(newFoodOption.getPriceOfFoodOption());
        foodOption.setSizeOfFoodOption(newFoodOption.getSizeOfFoodOption());

        FoodOption updatedFoodOption = foodOptionRepository.save(foodOption);
        if(updatedFoodOption == null)
            throw new ErrorExecutionFailedException("FoodOption update failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"FoodOption successfully updated ",updatedFoodOption));

    }

    public ResponseEntity<ResponseObject> getFoodOptionById(Long id) throws ErrorNotFoundException {
        FoodOption foodOption = foodOptionRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find foodOption with id "+id));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find successful foodOption with id "+id,foodOption));
    }

    public ResponseEntity<ResponseObject> deleteFoodOptionById(Long id) throws ErrorNotFoundException {
        FoodOption foodOption = foodOptionRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find foodOption with id "+id));
        foodOptionRepository.delete(foodOption);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful foodOption with id "+id,foodOption));
    }
}
