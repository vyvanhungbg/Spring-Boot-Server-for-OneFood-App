package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.model.Store;
import com.OneFood.ServerOneFood.reponsitory.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    @Autowired
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public ResponseEntity<ResponseObject> addNewStore(Store store){
        Store savedStore = storeRepository.save(store);
        if(savedStore == null)
            return  ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"New store save failed !", store));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New store save successful ! ", store));
    }
    public ResponseEntity<ResponseObject>  getAllStore(){
        List<Store> stores =  storeRepository.findAll();
        if(stores.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty store list ", stores));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find all successful stores ", stores));
    }
    public ResponseEntity<ResponseObject> getStoreById(Long id){
        Store store = storeRepository.findById(id).orElse(null);
        if(store == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Can not find store with id "+id, null));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find a successful store with id "+id, store));
    }


    public ResponseEntity<ResponseObject> updateStoreById(Long id, Store newStore)  {
        Store store = storeRepository.findById(id).orElse(null);
        if(store==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find store with id "+id,null));
        store.setStoreName(newStore.getStoreName());
        store.setStoreAddress(newStore.getStoreAddress());
        store.setStoreImage(newStore.getStoreImage());
        store.setStoreWorkTime(newStore.getStoreWorkTime());
        store.setStoreLatitude(newStore.getStoreLatitude());
        store.setStoreLongitude(newStore.getStoreLongitude());
        Store updatedStore = storeRepository.save(store);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Update successful store with id "+id,updatedStore));

    }

    public ResponseEntity<ResponseObject> deleteStoreById(Long id) {
        Store store = storeRepository.findById(id).orElse(null);
        if(store==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find type of food with id "+id,null));
        storeRepository.delete(store);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful type of food with id "+id,store));
    }
}
