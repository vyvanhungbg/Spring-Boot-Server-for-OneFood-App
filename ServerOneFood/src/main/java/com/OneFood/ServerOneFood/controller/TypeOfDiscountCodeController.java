package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.model.TypeOfDiscountCode;
import com.OneFood.ServerOneFood.service.TypeOfDiscountCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("one-app/v1/type-of-discount")

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
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@Valid  @RequestBody TypeOfDiscountCode typeOfDiscountCode) throws ErrorExecutionFailedException {
        return typeOfDiscountCodeService.addNewTypeOfDiscount(typeOfDiscountCode);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getTypeOfDiscountCodeById(@PathVariable Long id) throws ErrorNotFoundException {
        return typeOfDiscountCodeService.getTypeOfDiscountCodeById(id);
    }



    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> updateTypeOfDiscountCodeById(@PathVariable(value = "id") Long id,@Valid @RequestBody TypeOfDiscountCode newTypeOfDiscount) throws ErrorExecutionFailedException, ErrorNotFoundException {
        return typeOfDiscountCodeService.updateTypeOfDiscountCodeById(id,newTypeOfDiscount);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> deleteTypeOfDiscountCodeById(@PathVariable(value = "id") Long id) throws ErrorNotFoundException {
        return typeOfDiscountCodeService.deleteTypeOfDiscountCodeById(id);
    }
}
