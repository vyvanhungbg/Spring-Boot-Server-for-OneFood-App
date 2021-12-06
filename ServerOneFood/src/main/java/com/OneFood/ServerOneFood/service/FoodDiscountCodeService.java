package com.OneFood.ServerOneFood.service;

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

    public ResponseEntity<ResponseObject> addNewDiscount(FoodDiscountCode foodDiscountCode){
        FoodDiscountCode savedFoodDiscountCode =foodDiscountCodeRepository.save(foodDiscountCode);
        if(savedFoodDiscountCode == null)
            return  ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"New discount save failed !", null));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New discount save successful ! ", savedFoodDiscountCode));

    }

    public ResponseEntity<ResponseObject> updateDiscountById(Long id, FoodDiscountCode newFoodDiscountCode)  {
        FoodDiscountCode foodDiscountCode = foodDiscountCodeRepository.findById(id).orElse(null);
        if(foodDiscountCode==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find type of food with id "+id,null));
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
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Update successful discount with id "+id,foodDiscountCode));

    }

    public ResponseEntity<ResponseObject> getDiscountById(Long id) {
       FoodDiscountCode foodDiscountCode = foodDiscountCodeRepository.findById(id).orElse(null);
        if(foodDiscountCode!=null){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Successful search discount with id "+id,foodDiscountCode));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find discount with id "+id,null));
    }

    public ResponseEntity<ResponseObject> deleteDiscountById(Long id) {
        FoodDiscountCode foodDiscountCode = foodDiscountCodeRepository.findById(id).orElse(null);
        if(foodDiscountCode==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Can not find discount with id "+id,null));
        foodDiscountCodeRepository.delete(foodDiscountCode);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful discount with id "+id,foodDiscountCode));

    }
}
