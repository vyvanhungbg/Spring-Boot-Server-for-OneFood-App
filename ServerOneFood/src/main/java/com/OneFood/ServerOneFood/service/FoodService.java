package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.DTO.FoodDTO;
import com.OneFood.ServerOneFood.DTO.UserDTO;
import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.*;
import com.OneFood.ServerOneFood.reponsitory.FoodDiscountCodeRepository;
import com.OneFood.ServerOneFood.reponsitory.FoodRepository;
import com.OneFood.ServerOneFood.reponsitory.TypeOfFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final TypeOfFoodRepository typeOfFoodRepository;
    private final FoodDiscountCodeRepository foodDiscountCodeRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository, TypeOfFoodRepository typeOfFoodRepository, FoodDiscountCodeRepository foodDiscountCodeRepository) {
        this.foodRepository = foodRepository;

        this.typeOfFoodRepository = typeOfFoodRepository;
        this.foodDiscountCodeRepository = foodDiscountCodeRepository;
    }

    public ResponseEntity<ResponseObject> getAllFood(boolean sorted, float star, int page) throws ErrorExecutionFailedException {
        List<FoodDTO> foods =  foodRepository.getAllFoodDTO();
        if(foods.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty food list ", foods));
        List<FoodDTO> foodDTOS = new ArrayList<>(foods);
        if(star!=-1)
            foodDTOS = foods.stream().filter(foodDTO -> Double.parseDouble(foodDTO.getFoodStar())>star).collect(Collectors.toList());
        int PAGE_SIZE = 4;
        int limitStart = (page-1)*PAGE_SIZE;
        int limitEnd = (page)*PAGE_SIZE;
        if(limitStart > foods.size())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(false,"End list", new ArrayList<>()));
        if(page>0 && limitStart != -1 && limitEnd !=-1 && limitStart<limitEnd)
            foodDTOS = foods.stream().skip(limitStart).limit(limitEnd-limitStart).collect(Collectors.toList());
        if(sorted)
            foodDTOS = foodDTOS.stream().sorted((o1, o2) -> Double.parseDouble(o1.getFoodPrice()) > Double.parseDouble(o2.getFoodPrice())?1:0).collect(Collectors.toList());

        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find "+foodDTOS.size()+" food successful  ", foodDTOS));

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


    public ResponseEntity<ResponseObject> addFoodDiscountCodeByIdFoodAndIdDiscount(Long idFood, Long idDiscount) throws ErrorNotFoundException, ErrorExecutionFailedException {
        Food food = foodRepository.findById(idFood).orElseThrow(() -> new ErrorNotFoundException("Can not find food with id "+idFood));
        List<FoodDiscountCode> foodDiscountCodes = food.getFoodDiscountCodes();
        FoodDiscountCode foodDiscountCode = foodDiscountCodeRepository.findById(idDiscount).orElseThrow(() -> new ErrorExecutionFailedException("Cannot find Food Discount with id "+idDiscount));
        if(!foodDiscountCodes.stream().filter(discountCode -> discountCode.getIdFoodDiscountCode()==idDiscount).findFirst().isEmpty())
            throw new ErrorExecutionFailedException("Food has this discount code with id "+idDiscount);
        try {
            food.addDiscount(foodDiscountCode);
            foodRepository.save(food);
        }catch (Exception e){
            throw new ErrorExecutionFailedException(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"add food discount code successful for use with id "+idFood,food));
    }

    public ResponseEntity<ResponseObject> removeFoodDiscountCodeByIdFoodAndIdDiscount(Long idFood, Long idDiscount) throws ErrorNotFoundException, ErrorExecutionFailedException {
        Food food = foodRepository.findById(idFood).orElseThrow(() -> new ErrorNotFoundException("Can not find food with id "+idFood));
        List<FoodDiscountCode> foodDiscountCodes = food.getFoodDiscountCodes();
        FoodDiscountCode foodDiscountCode = foodDiscountCodeRepository.findById(idDiscount).orElseThrow(() -> new ErrorExecutionFailedException("Cannot find Food Discount with id "+idDiscount));
        if(foodDiscountCodes.stream().filter(discountCode -> discountCode.getIdFoodDiscountCode()==idDiscount).findFirst().isEmpty())
            throw new ErrorExecutionFailedException("Food hasn't this discount code with id "+idDiscount);
        try {
            food.removeDiscount(foodDiscountCode);
            foodRepository.save(food);
        }catch (Exception e){
            throw new ErrorExecutionFailedException(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"remove food discount code successful for use with id "+idFood,food));
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

    public ResponseEntity<ResponseObject> updatePriceFoodWhileFlashSaleById(Long id, String newPrice) throws ErrorNotFoundException {
        Food food = foodRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find food with id "+id));
        food.setFoodPrice(newPrice);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Update successful food with id "+id,""));

    }



    public ResponseEntity<ResponseObject> deleteFoodById(Long id) throws ErrorNotFoundException, ErrorExecutionFailedException {
        Food food = foodRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find food with id "+id));
        foodRepository.delete(food);
        if(foodRepository.existsById(id))
            throw new ErrorExecutionFailedException("Delete food failed with id "+id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful food with id "+id,food));

    }

}
