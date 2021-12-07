package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.exception.ErrorAccessDeniedException;
import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.LocationOfUser;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.reponsitory.LocationOfUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationOfUserService {

    @Autowired
    private final LocationOfUserRepository locationOfUserRepository;

    @Autowired
    private final MyService myService;
    public LocationOfUserService(LocationOfUserRepository locationOfUserRepository, MyService myService) {
        this.locationOfUserRepository = locationOfUserRepository;
        this.myService = myService;
    }


    public ResponseEntity<ResponseObject> getAllLocationOfUser(){
        List<LocationOfUser> locationOfUsers =  locationOfUserRepository.findAll();
        if(locationOfUsers.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty location of user list ", locationOfUsers));

        List<LocationOfUser> locationOfUsersFilter =  new ArrayList<>(locationOfUsers);
        Long idUser = myService.getPrincipal();
        if(!myService.isRoleAdmin())
            locationOfUsersFilter = locationOfUsers.stream().filter(location -> location.getIdUser() == idUser).collect(Collectors.toList());
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find "+locationOfUsersFilter.size()+" location of user ", locationOfUsersFilter));

    }

    public ResponseEntity<ResponseObject> addNewLocationOfUser(LocationOfUser locationOfUser) throws ErrorExecutionFailedException {
        if(locationOfUserRepository.existsById(locationOfUser.getIdLocationOfUser()))
            throw new ErrorExecutionFailedException("New location create failed Because this item already exists");
        Long idUser = myService.getPrincipal();
        locationOfUser.setIdUser(idUser);
        LocationOfUser savedLocationOfUser = locationOfUserRepository.save(locationOfUser);
        if(savedLocationOfUser == null)
           throw new ErrorExecutionFailedException("New location of user create failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New location of user successfully created ",savedLocationOfUser));

    }

    public ResponseEntity<ResponseObject> updateLocationOfUserById(Long id, LocationOfUser newLocationOfUser) throws ErrorNotFoundException, ErrorExecutionFailedException,  ErrorAccessDeniedException {
        LocationOfUser locationOfUser = locationOfUserRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find location of user with id "+id));
        Long idUser = myService.getPrincipal();
        if(idUser!=locationOfUser.getIdUser())
            throw new ErrorAccessDeniedException("Access is denied");
        locationOfUser.setNameOfLocation(locationOfUser.getNameOfLocation());
        locationOfUser.setLongitude(locationOfUser.getLongitude());
        locationOfUser.setLatitude(locationOfUser.getLatitude());
        locationOfUser.setNameOfReceiver(locationOfUser.getNameOfReceiver());
        locationOfUser.setNumberPhoneOfReceiver(locationOfUser.getNumberPhoneOfReceiver());


        LocationOfUser updatedLocationOfUser = locationOfUserRepository.save(locationOfUser);
        if(updatedLocationOfUser == null)
                throw new ErrorExecutionFailedException("Location of user update failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Location of user successfully updated ",updatedLocationOfUser));

    }

    public ResponseEntity<ResponseObject> getLocationOfUserById(Long id) throws ErrorNotFoundException, ErrorAccessDeniedException {
        LocationOfUser locationOfUser = locationOfUserRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find location of user with id "+id));
        Long idUser = myService.getPrincipal();
        if(idUser!=locationOfUser.getIdUser())
            throw new ErrorAccessDeniedException("Access is denied");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find successful location of user with id "+id,locationOfUser));
    }

    public ResponseEntity<ResponseObject> deleteLocationOfUserById(Long id) throws ErrorNotFoundException, ErrorExecutionFailedException, ErrorAccessDeniedException {
        LocationOfUser locationOfUser = locationOfUserRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find location of user with id "+id));
        Long idUser = myService.getPrincipal();
        if(idUser!=locationOfUser.getIdUser())
            throw new ErrorAccessDeniedException("Access is denied");
        locationOfUserRepository.delete(locationOfUser);
         if(locationOfUserRepository.existsById(id))
             throw new ErrorExecutionFailedException("Delete failed location with id "+id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful food review with id "+id,locationOfUser));
    }

    public ResponseEntity<ResponseObject> updateDefaultLocationOfUserById(Long id) throws ErrorNotFoundException, ErrorExecutionFailedException, ErrorAccessDeniedException {
        LocationOfUser locationOfUser = locationOfUserRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find location of user with id "+id));
        if(locationOfUser.isDefaultLocation())
            ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Default location of user successfully updated ",locationOfUser));
        Long idUser = myService.getPrincipal();
        if(idUser!=locationOfUser.getIdUser())
            throw new ErrorAccessDeniedException("Access is denied");
        List<LocationOfUser> defaultLocationTrues = locationOfUserRepository.findByIsDefaultLocation(true);
        if(!defaultLocationTrues.isEmpty()){  // set nhung location true thanh false
            for (LocationOfUser item : defaultLocationTrues){
                item.setDefaultLocation(false);
                if(locationOfUserRepository.save(item)==null){
                    throw new ErrorExecutionFailedException("Default location of user update failed ");
                }
            }
        }

        locationOfUser.setDefaultLocation(true);
        LocationOfUser updatedLocationOfUser = locationOfUserRepository.save(locationOfUser);
        if(updatedLocationOfUser == null)
            throw new ErrorExecutionFailedException("Default location of user update failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Default location of user successfully updated ",updatedLocationOfUser));

    }
}
