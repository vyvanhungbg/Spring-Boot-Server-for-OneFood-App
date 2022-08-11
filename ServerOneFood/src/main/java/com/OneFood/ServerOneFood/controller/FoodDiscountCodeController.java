package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.FoodDiscountCode;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.service.FoodDiscountCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("one-app/v1/food-discount-code")
public class FoodDiscountCodeController {
    @Autowired
    private FoodDiscountCodeService foodDiscountCodeService;

    public FoodDiscountCodeController(FoodDiscountCodeService foodDiscountCodeService) {
        this.foodDiscountCodeService = foodDiscountCodeService;
    }

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllStore(@RequestParam(value = "page", defaultValue = "0") int page){
        return foodDiscountCodeService.getAllDiscount(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getDiscountById(@PathVariable Long id) throws ErrorNotFoundException {
        return foodDiscountCodeService.getDiscountById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("")
    public ResponseEntity<ResponseObject> addNewDiscount(@Valid @RequestBody FoodDiscountCode foodDiscountCode) throws ErrorExecutionFailedException {
        return foodDiscountCodeService.addNewDiscount(foodDiscountCode);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> updateDiscountById(@PathVariable(value = "id") Long id, @Valid @RequestBody FoodDiscountCode newDiscount) throws ErrorExecutionFailedException, ErrorNotFoundException {
        return foodDiscountCodeService.updateDiscountById(id,newDiscount);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteDiscountById(@PathVariable(value = "id") Long id) throws ErrorNotFoundException, ErrorExecutionFailedException {
        return foodDiscountCodeService.deleteDiscountById(id);
    }
}
