package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.model.LocationOfUser;

import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.service.LocationOfUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("location-default-of-user")
public class LocationOfUserController {

    @Autowired
    private final LocationOfUserService locationOfUserService;

    public LocationOfUserController(LocationOfUserService locationOfUserService) {
        this.locationOfUserService = locationOfUserService;
    }


    @GetMapping("")
    ResponseEntity<ResponseObject> getAllLocationOfUser(){
        return locationOfUserService.getAllLocationOfUser();
    }
    @PostMapping("")
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@RequestBody LocationOfUser user){
        return locationOfUserService.addNewLocationOfUser(user);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getLocationOfUsersById(@PathVariable Long id){
        return locationOfUserService.getLocationOfUserById(id);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateLocationOfUsersById(@PathVariable(value = "id") Long id, @RequestBody LocationOfUser newLocationOfUser)  {
        return locationOfUserService.updateLocationOfUserById(id,newLocationOfUser);
    }

    @PutMapping("/set-default-location/{id}")
    public ResponseEntity<ResponseObject> updateDefaultLocationOfUsersById(@PathVariable(value = "id") Long id)  {
        return locationOfUserService.updateDefaultLocationOfUserById(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteLocationOfUsersById(@PathVariable(value = "id") Long id){
        return locationOfUserService.deleteLocationOfUserById(id);
    }
}
