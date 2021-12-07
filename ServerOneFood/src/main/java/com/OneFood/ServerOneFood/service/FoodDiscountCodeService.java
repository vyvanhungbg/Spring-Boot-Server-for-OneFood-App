package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.FoodDiscountCode;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.reponsitory.FoodDiscountCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodDiscountCodeService {

    @Autowired
    private final FoodDiscountCodeRepository foodDiscountCodeRepository;

    public FoodDiscountCodeService(FoodDiscountCodeRepository foodDiscountCodeRepository) {
        this.foodDiscountCodeRepository = foodDiscountCodeRepository;
    }


    public ResponseEntity<ResponseObject> getAllDiscount(){
        List<FoodDiscountCode> foodDiscountCodes =  foodDiscountCodeRepository.findAll();
        if(foodDiscountCodes.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty type of discount list ", foodDiscountCodes));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find all successful discounts  ", foodDiscountCodes));

    }

    public ResponseEntity<ResponseObject> addNewDiscount(FoodDiscountCode foodDiscountCode) throws ErrorExecutionFailedException {
        if(foodDiscountCodeRepository.existsById(foodDiscountCode.getIdFoodDiscountCode()))
            throw new ErrorExecutionFailedException("New food discount create failed Because this item already exists");
        FoodDiscountCode savedFoodDiscountCode =foodDiscountCodeRepository.save(foodDiscountCode);
        if(savedFoodDiscountCode == null)
            throw new ErrorExecutionFailedException("New discount save failed !");
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New discount save successful ! ", savedFoodDiscountCode));

    }

    public ResponseEntity<ResponseObject> updateDiscountById(Long id, FoodDiscountCode newFoodDiscountCode) throws ErrorNotFoundException, ErrorExecutionFailedException {
        FoodDiscountCode foodDiscountCode = foodDiscountCodeRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find type of food with id "+id));

       foodDiscountCode.setFoodDiscountCodeByMoney(newFoodDiscountCode.getFoodDiscountCodeByMoney());
       foodDiscountCode.setFoodDiscountCodeByPercent(newFoodDiscountCode.getFoodDiscountCodeByPercent());
       foodDiscountCode.setFoodDiscountCodeDescribe(newFoodDiscountCode.getFoodDiscountCodeDescribe());
       foodDiscountCode.setFoodDiscountCodeImage(newFoodDiscountCode.getFoodDiscountCodeImage());
       foodDiscountCode.setFoodDiscountCodeByPaymentMethod(newFoodDiscountCode.getFoodDiscountCodeByPaymentMethod());
       foodDiscountCode.setFoodDiscountCodeStatus(newFoodDiscountCode.getFoodDiscountCodeStatus());
       foodDiscountCode.setFoodDiscountCodeIsDestroy(newFoodDiscountCode.getFoodDiscountCodeIsDestroy());
       foodDiscountCode.setFoodDiscountEndTime(newFoodDiscountCode.getFoodDiscountEndTime());
       foodDiscountCode.setFoodDiscountStartTime(newFoodDiscountCode.getFoodDiscountStartTime());
        foodDiscountCode.setIdTypeOfDiscountCode(newFoodDiscountCode.getIdTypeOfDiscountCode());
        FoodDiscountCode updatedFoodDiscountCode = foodDiscountCodeRepository.save(foodDiscountCode);
        if(updatedFoodDiscountCode == null) throw new ErrorExecutionFailedException("Update failed food discout with id "+id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Update successful discount with id "+id,foodDiscountCode));

    }

    public ResponseEntity<ResponseObject> getDiscountById(Long id) throws ErrorNotFoundException {
       FoodDiscountCode foodDiscountCode = foodDiscountCodeRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find discount with id "+id));

       return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Successful search discount with id "+id,foodDiscountCode));

    }

    public ResponseEntity<ResponseObject> deleteDiscountById(Long id) throws ErrorNotFoundException, ErrorExecutionFailedException {
        FoodDiscountCode foodDiscountCode = foodDiscountCodeRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Can not find discount with id "+id));
        foodDiscountCodeRepository.delete(foodDiscountCode);
        if(foodDiscountCodeRepository.existsById(id)) throw new ErrorExecutionFailedException("Delete failed food discount with id "+id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful discount with id "+id,foodDiscountCode));

    }
}
