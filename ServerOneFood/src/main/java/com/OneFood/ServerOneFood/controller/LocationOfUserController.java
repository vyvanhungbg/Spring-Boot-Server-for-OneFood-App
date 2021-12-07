package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorAccessDeniedException;
import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.LocationOfUser;

import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.service.LocationOfUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("one-app/v1/location-of-user")
@PreAuthorize("isAuthenticated()")
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
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@Valid @RequestBody LocationOfUser user) throws ErrorExecutionFailedException {
        return locationOfUserService.addNewLocationOfUser(user);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getLocationOfUsersById(@PathVariable Long id) throws ErrorNotFoundException, ErrorAccessDeniedException {
        return locationOfUserService.getLocationOfUserById(id);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateLocationOfUsersById(@PathVariable(value = "id") Long id, @Valid @RequestBody LocationOfUser newLocationOfUser) throws ErrorExecutionFailedException, ErrorNotFoundException,  ErrorAccessDeniedException {
        return locationOfUserService.updateLocationOfUserById(id,newLocationOfUser);
    }

    @PutMapping("/set-default-location/{id}")
    public ResponseEntity<ResponseObject> updateDefaultLocationOfUsersById(@PathVariable(value = "id") Long id) throws ErrorExecutionFailedException, ErrorNotFoundException,  ErrorAccessDeniedException {
        return locationOfUserService.updateDefaultLocationOfUserById(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteLocationOfUsersById(@PathVariable(value = "id") Long id) throws ErrorExecutionFailedException, ErrorNotFoundException, ErrorAccessDeniedException {
        return locationOfUserService.deleteLocationOfUserById(id);
    }
}
