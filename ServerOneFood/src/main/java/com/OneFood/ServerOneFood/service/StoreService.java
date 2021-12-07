package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
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

    public ResponseEntity<ResponseObject> addNewStore(Store store) throws ErrorExecutionFailedException {
        Store savedStore = storeRepository.save(store);
        if(savedStore == null)
            throw new ErrorExecutionFailedException("New store save failed !");
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New store save successful ! ", store));
    }

    public ResponseEntity<ResponseObject>  getAllStore(){
        List<Store> stores =  storeRepository.findAll();
        if(stores.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty store list ", stores));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find "+stores.size()+" stores ", stores));
    }
    public ResponseEntity<ResponseObject> getStoreById(Long id) throws ErrorNotFoundException {
        Store store = storeRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Can not find store with id "+id));

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find a successful store with id "+id, store));
    }


    public ResponseEntity<ResponseObject> updateStoreById(Long id, Store newStore) throws ErrorNotFoundException, ErrorExecutionFailedException {
        Store store = storeRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Can not find store with id "+id));     store.setStoreName(newStore.getStoreName());
        store.setStoreAddress(newStore.getStoreAddress());
        store.setStoreImage(newStore.getStoreImage());
        store.setStoreWorkTime(newStore.getStoreWorkTime());
        store.setStoreLatitude(newStore.getStoreLatitude());
        store.setStoreLongitude(newStore.getStoreLongitude());
        Store updatedStore = storeRepository.save(store);
        if(updatedStore == null)
            throw new ErrorExecutionFailedException("Update store failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Update successful store with id "+id,updatedStore));

    }

    public ResponseEntity<ResponseObject> deleteStoreById(Long id) throws ErrorNotFoundException, ErrorExecutionFailedException {
        Store store = storeRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Can not find store with id "+id));
        storeRepository.delete(store);
        if(storeRepository.existsById(id))
            throw new ErrorExecutionFailedException("Delete store failed with id "+id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful type of food with id "+id,store));
    }
}
