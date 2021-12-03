package com.OneFood.ServerOneFood.Service;

import com.OneFood.ServerOneFood.Model.LocationOfUser;
import com.OneFood.ServerOneFood.Model.ResponseObject;
import com.OneFood.ServerOneFood.Reponsitory.LocationOfUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationOfUserService {

    @Autowired
    private final LocationOfUserRepository locationOfUserRepository;

    public LocationOfUserService(LocationOfUserRepository locationOfUserRepository) {
        this.locationOfUserRepository = locationOfUserRepository;
    }


    public ResponseEntity<ResponseObject> getAllLocationOfUser(){
        List<LocationOfUser> locationOfUsers =  locationOfUserRepository.findAll();
        if(locationOfUsers.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty location of user list ", locationOfUsers));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find all successful location of user ", locationOfUsers));

    }

    public ResponseEntity<ResponseObject> addNewLocationOfUser(LocationOfUser locationOfUser){
        LocationOfUser savedLocationOfUser = locationOfUserRepository.save(locationOfUser);
        if(savedLocationOfUser == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"New location of user create failed ",savedLocationOfUser));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New location of user successfully created ",savedLocationOfUser));

    }

    public ResponseEntity<ResponseObject> updateLocationOfUserById(Long id, LocationOfUser newLocationOfUser)  {
        LocationOfUser locationOfUser = locationOfUserRepository.findById(id).orElse(null);
        if(locationOfUser==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find location of user with id "+id,null));
        locationOfUser.setNameOfLocation(locationOfUser.getNameOfLocation());
        locationOfUser.setLongitude(locationOfUser.getLongitude());
        locationOfUser.setLatitude(locationOfUser.getLatitude());
        locationOfUser.setNameOfReceiver(locationOfUser.getNameOfReceiver());
        locationOfUser.setNumberPhoneOfReceiver(locationOfUser.getNumberPhoneOfReceiver());


        LocationOfUser updatedLocationOfUser = locationOfUserRepository.save(locationOfUser);
        if(updatedLocationOfUser == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"Location of user update failed ",updatedLocationOfUser));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Location of user successfully updated ",updatedLocationOfUser));

    }

    public ResponseEntity<ResponseObject> getLocationOfUserById(Long id) {
        LocationOfUser locationOfUser = locationOfUserRepository.findById(id).orElse(null);
        if(locationOfUser==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find location of user with id "+id,null));

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find successful location of user with id "+id,locationOfUser));
    }

    public ResponseEntity<ResponseObject> deleteLocationOfUserById(Long id) {
        LocationOfUser locationOfUser = locationOfUserRepository.findById(id).orElse(null);
        if(locationOfUser==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find food review with id "+id,null));
        locationOfUserRepository.delete(locationOfUser);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful food review with id "+id,locationOfUser));
    }

    public ResponseEntity<ResponseObject> updateDefaultLocationOfUserById(Long id)  {
        LocationOfUser locationOfUser = locationOfUserRepository.findById(id).orElse(null);
        List<LocationOfUser> defaultLocationTrues = locationOfUserRepository.findByIsDefaultLocation(true);
        if(!defaultLocationTrues.isEmpty()){  // set nhung location true thanh false
            for (LocationOfUser item : defaultLocationTrues){
                item.setDefaultLocation(false);
                if(locationOfUserRepository.save(item)==null){
                    return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"Default location of user update failed ",item));
                }
            }
        }

        if(locationOfUser==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find location of user with id "+id,null));
        locationOfUser.setDefaultLocation(true);


        LocationOfUser updatedLocationOfUser = locationOfUserRepository.save(locationOfUser);
        if(updatedLocationOfUser == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"Default location of user update failed ",updatedLocationOfUser));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Default location of user successfully updated ",updatedLocationOfUser));

    }
}
