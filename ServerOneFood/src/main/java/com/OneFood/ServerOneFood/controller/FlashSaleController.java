package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.model.FlashSale;
import com.OneFood.ServerOneFood.service.FlashSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("one-app/v1/flash-sale")

public class FlashSaleController {

    @Autowired
    private final FlashSaleService flashSaleService;

    public FlashSaleController(FlashSaleService flashSaleService) {
        this.flashSaleService = flashSaleService;
    }


    @GetMapping("")
    ResponseEntity<ResponseObject> getAllFlashSale(@RequestParam(value = "page", defaultValue = "0") int page){
        return flashSaleService.getAllFlashSale(page);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<ResponseObject> addNewFlashSale(@Valid @RequestBody FlashSale flashSale) throws ErrorExecutionFailedException {
        return flashSaleService.addNewFlashSale(flashSale);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getFlashSaleById(@Valid @PathVariable Long id) throws ErrorNotFoundException {
        return flashSaleService.getFlashSaleById(id);
    }



    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> updateFlashSaleById(@PathVariable(value = "id") Long id, @RequestBody FlashSale newFlashSale) throws ErrorExecutionFailedException, ErrorNotFoundException {
        return flashSaleService.updateFlashSaleById(id,newFlashSale);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> deleteFlashSaleById(@PathVariable(value = "id") Long id) throws ErrorExecutionFailedException, ErrorNotFoundException {
        return flashSaleService.deleteFlashSaleById(id);
    }
}
