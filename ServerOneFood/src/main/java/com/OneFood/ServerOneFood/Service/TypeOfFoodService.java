package com.OneFood.ServerOneFood.Service;


import com.OneFood.ServerOneFood.Exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.Model.ResponseObject;
import com.OneFood.ServerOneFood.Model.TypeOfFood;
import com.OneFood.ServerOneFood.Reponsitory.TypeOfFoodRepository;
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
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find all successful type of food ", typeOfFoods));

    }

    public ResponseEntity<ResponseObject> addNewTypeOfFood(TypeOfFood typeOfFood){
        TypeOfFood savedTypeOfFood =typeOfFoodRepository.save(typeOfFood);
        if(savedTypeOfFood == null)
            return  ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"New type of food save failed !", null));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New type of food save successful ! ", savedTypeOfFood));

    }

    public ResponseEntity<ResponseObject> updateTypeOfFoodById(Long id, TypeOfFood newTypeOfFood)  {
        TypeOfFood typeOfFood = typeOfFoodRepository.findById(id).orElse(null);
        if(typeOfFood==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find type of food with id "+id,null));
        typeOfFood.setTypeOfFoodName(newTypeOfFood.getTypeOfFoodName());
        typeOfFood.setFoods(newTypeOfFood.getFoods());
        typeOfFood.setTypeOfFoodImage(newTypeOfFood.getTypeOfFoodImage());

        TypeOfFood updatedTypeOfFood = typeOfFoodRepository.save(typeOfFood);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Update successful type of food with id "+id,updatedTypeOfFood));

    }

    public ResponseEntity<ResponseObject> getTypeOfFoodById(Long id) {
        Optional<TypeOfFood> typeOfFood = typeOfFoodRepository.findById(id);
        if(typeOfFood.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Successful search type of food with id "+id,typeOfFood));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find type food with id "+id,null));
    }

    public ResponseEntity<ResponseObject> deleteTypeOfFoodById(Long id) {
        TypeOfFood typeOfFood = typeOfFoodRepository.findById(id).orElse(null);
        if(typeOfFood==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find type of food with id "+id,null));
        typeOfFoodRepository.delete(typeOfFood);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful type of food with id "+id,typeOfFood));

    }
}
