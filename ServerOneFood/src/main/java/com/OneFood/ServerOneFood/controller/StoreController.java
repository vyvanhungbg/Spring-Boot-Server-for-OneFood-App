package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.model.Store;
import com.OneFood.ServerOneFood.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllStore(){
        return storeService.getAllStore();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getStoreById(@PathVariable Long id){
        return storeService.getStoreById(id);
    }

    @PostMapping("")
    public ResponseEntity<ResponseObject> addNewStore(@RequestBody Store store){
        return storeService.addNewStore(store);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateStoreById(@PathVariable(value = "id") Long id, @RequestBody Store newStore)  {
        return storeService.updateStoreById(id,newStore);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteStoreById(@PathVariable(value = "id") Long id){
        return storeService.deleteStoreById(id);
    }
}
