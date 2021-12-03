package com.OneFood.ServerOneFood.Controller;

import com.OneFood.ServerOneFood.Model.ResponseObject;
import com.OneFood.ServerOneFood.Model.TypeOfDiscountCode;
import com.OneFood.ServerOneFood.Model.TypeOfFood;
import com.OneFood.ServerOneFood.Service.TypeOfDiscountCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("type-of-discount")
public class TypeOfDiscountCodeController {
    @Autowired
    private final TypeOfDiscountCodeService typeOfDiscountCodeService;

    public TypeOfDiscountCodeController(TypeOfDiscountCodeService typeOfDiscountCodeService) {
        this.typeOfDiscountCodeService = typeOfDiscountCodeService;
    }


    @GetMapping("")
    ResponseEntity<ResponseObject> getAllTypeOfDiscountCode(){
        return typeOfDiscountCodeService.getAllTypeOfDiscountCode();
    }
    @PostMapping("")
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@RequestBody TypeOfDiscountCode typeOfDiscountCode){
        return typeOfDiscountCodeService.addNewTypeOfDiscount(typeOfDiscountCode);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getTypeOfDiscountCodeById(@PathVariable Long id){
        return typeOfDiscountCodeService.getTypeOfDiscountCodeById(id);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateTypeOfDiscountCodeById(@PathVariable(value = "id") Long id, @RequestBody TypeOfDiscountCode newTypeOfDiscount)  {
        return typeOfDiscountCodeService.updateTypeOfDiscountCodeById(id,newTypeOfDiscount);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteTypeOfDiscountCodeById(@PathVariable(value = "id") Long id){
        return typeOfDiscountCodeService.deleteTypeOfDiscountCodeById(id);
    }
}
