package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.model.TypeOfFood;
import com.OneFood.ServerOneFood.service.TypeOfFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("one-app/v1/type-of-food")
public class TypeOfFoodController {
    @Autowired
    private final TypeOfFoodService typeOfFoodService;

    public TypeOfFoodController(TypeOfFoodService typeOfFoodService) {
        this.typeOfFoodService = typeOfFoodService;
    }

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllTypeOfFood(){
        return typeOfFoodService.getAllTypeOfFood();
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<ResponseObject> addNewTypeOfFood(@Valid  @RequestBody TypeOfFood typeOfFood) throws ErrorExecutionFailedException {
        return typeOfFoodService.addNewTypeOfFood(typeOfFood);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getTypeOfFoodById(@Valid @PathVariable Long id) throws ErrorNotFoundException {
        return typeOfFoodService.getTypeOfFoodById(id);
    }



    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> updateTypeOfFoodById(@PathVariable(value = "id") Long id, @RequestBody TypeOfFood newTypeOfFood) throws ErrorExecutionFailedException, ErrorNotFoundException {
        return typeOfFoodService.updateTypeOfFoodById(id,newTypeOfFood);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> deleteTypeOfFoodById(@PathVariable(value = "id") Long id) throws ErrorExecutionFailedException, ErrorNotFoundException {
        return typeOfFoodService.deleteTypeOfFoodById(id);
    }
}
