package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.model.Store;
import com.OneFood.ServerOneFood.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/one-app/v1/store")

public class StoreController {
    @Autowired
    private StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllStore(@RequestParam(value = "page", defaultValue = "0") int page){
        return storeService.getAllStore(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getStoreById(@PathVariable Long id) throws ErrorNotFoundException {
        return storeService.getStoreById(id);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> addNewStore(@Valid  @RequestBody Store store) throws ErrorExecutionFailedException {
        return storeService.addNewStore(store);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> updateStoreById(@PathVariable(value = "id") Long id,@Valid @RequestBody Store newStore) throws ErrorExecutionFailedException, ErrorNotFoundException {
        return storeService.updateStoreById(id,newStore);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> deleteStoreById(@PathVariable(value = "id") Long id) throws ErrorExecutionFailedException, ErrorNotFoundException {
        return storeService.deleteStoreById(id);
    }
}
