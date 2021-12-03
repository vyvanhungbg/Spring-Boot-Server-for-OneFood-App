package com.OneFood.ServerOneFood.Controller;

import com.OneFood.ServerOneFood.Model.ResponseObject;
import com.OneFood.ServerOneFood.Model.Store;
import com.OneFood.ServerOneFood.Model.TypeOfFood;
import com.OneFood.ServerOneFood.Service.TypeOfFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type-of-food")
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
    ResponseEntity<ResponseObject> addNewTypeOfFood(@RequestBody TypeOfFood typeOfFood){
        return typeOfFoodService.addNewTypeOfFood(typeOfFood);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getTypeOfFoodById(@PathVariable Long id){
        return typeOfFoodService.getTypeOfFoodById(id);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateTypeOfFoodById(@PathVariable(value = "id") Long id, @RequestBody TypeOfFood newTypeOfFood)  {
        return typeOfFoodService.updateTypeOfFoodById(id,newTypeOfFood);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteTypeOfFoodById(@PathVariable(value = "id") Long id){
        return typeOfFoodService.deleteTypeOfFoodById(id);
    }
}
