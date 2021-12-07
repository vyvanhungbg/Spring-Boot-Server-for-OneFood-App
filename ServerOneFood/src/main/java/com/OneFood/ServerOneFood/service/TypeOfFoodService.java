package com.OneFood.ServerOneFood.service;


import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.model.TypeOfFood;
import com.OneFood.ServerOneFood.reponsitory.TypeOfFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeOfFoodService {
    @Autowired
    private final TypeOfFoodRepository typeOfFoodRepository;

    public TypeOfFoodService(TypeOfFoodRepository typeOfFoodRepository) {
        this.typeOfFoodRepository = typeOfFoodRepository;
    }


    public ResponseEntity<ResponseObject> getAllTypeOfFood(){
        List<TypeOfFood> typeOfFoods =  typeOfFoodRepository.findAll();
        if(typeOfFoods.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty type of food list ", typeOfFoods));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find "+typeOfFoods.size()+" type of food ", typeOfFoods));

    }

    public ResponseEntity<ResponseObject> addNewTypeOfFood(TypeOfFood typeOfFood) throws ErrorExecutionFailedException {
        TypeOfFood savedTypeOfFood =typeOfFoodRepository.save(typeOfFood);
        if(savedTypeOfFood == null)
            throw new ErrorExecutionFailedException("New type of food save failed !");
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New type of food save successful ! ", savedTypeOfFood));

    }

    public ResponseEntity<ResponseObject> updateTypeOfFoodById(Long id, TypeOfFood newTypeOfFood) throws ErrorNotFoundException, ErrorExecutionFailedException {
        TypeOfFood typeOfFood = typeOfFoodRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find type of food with id "+id));

        typeOfFood.setTypeOfFoodName(newTypeOfFood.getTypeOfFoodName());
        typeOfFood.setFoods(newTypeOfFood.getFoods());
        typeOfFood.setTypeOfFoodImage(newTypeOfFood.getTypeOfFoodImage());

        TypeOfFood updatedTypeOfFood = typeOfFoodRepository.save(typeOfFood);
        if(updatedTypeOfFood == null)
            throw new ErrorExecutionFailedException("Update type of food failed with id "+id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Update successful type of food with id "+id,updatedTypeOfFood));

    }

    public ResponseEntity<ResponseObject> getTypeOfFoodById(Long id) throws ErrorNotFoundException {
        TypeOfFood typeOfFood = typeOfFoodRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find type of food with id "+id));
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Successful search type of food with id "+id,typeOfFood));
    }

    public ResponseEntity<ResponseObject> deleteTypeOfFoodById(Long id) throws ErrorNotFoundException, ErrorExecutionFailedException {
        TypeOfFood typeOfFood = typeOfFoodRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find type of food with id "+id)); typeOfFoodRepository.delete(typeOfFood);
        if(typeOfFoodRepository.existsById(id))
            throw new ErrorExecutionFailedException("Delete failed");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful type of food with id "+id,typeOfFood));
    }
}
