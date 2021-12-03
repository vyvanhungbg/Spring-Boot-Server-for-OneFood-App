package com.OneFood.ServerOneFood.Controller;

import com.OneFood.ServerOneFood.Model.FoodDiscountCode;
import com.OneFood.ServerOneFood.Model.ResponseObject;
import com.OneFood.ServerOneFood.Model.Store;
import com.OneFood.ServerOneFood.Service.FoodDiscountCodeService;
import com.OneFood.ServerOneFood.Service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("food-discount-code")
public class FoodDiscountCodeController {
    @Autowired
    private FoodDiscountCodeService foodDiscountCodeService;

    public FoodDiscountCodeController(FoodDiscountCodeService foodDiscountCodeService) {
        this.foodDiscountCodeService = foodDiscountCodeService;
    }

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllStore(){
        return foodDiscountCodeService.getAllDiscount();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getDiscountById(@PathVariable Long id){
        return foodDiscountCodeService.getDiscountById(id);
    }

    @PostMapping("")
    public ResponseEntity<ResponseObject> addNewDiscount(@RequestBody FoodDiscountCode foodDiscountCode){
        return foodDiscountCodeService.addNewDiscount(foodDiscountCode);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateDiscountById(@PathVariable(value = "id") Long id, @RequestBody FoodDiscountCode newDiscount)  {
        return foodDiscountCodeService.updateDiscountById(id,newDiscount);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteDiscountById(@PathVariable(value = "id") Long id){
        return foodDiscountCodeService.deleteDiscountById(id);
    }
}
