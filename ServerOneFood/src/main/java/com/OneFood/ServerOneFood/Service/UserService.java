package com.OneFood.ServerOneFood.Service;

import com.OneFood.ServerOneFood.DTO.UserDTO;
import com.OneFood.ServerOneFood.Model.*;
import com.OneFood.ServerOneFood.Reponsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<ResponseObject> getAllUser(){
        List<User> users =  userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        users.stream().forEach(user -> userDTOS.add(new UserDTO(user)));

        if(users.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty account list "+ userDTOS.size(), users));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find all successful account ", userDTOS));

    }

    public ResponseEntity<ResponseObject> addNewUser(User newUser){
        newUser.setUserMoney("0");
        newUser.setEnable(true);
        newUser.addRole(new Role("USER",new ArrayList<>()));
        User user = userRepository.save(newUser);
        if(user == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"New User create failed ",null));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New User successfully created ",new UserDTO(user)));

    }

    public ResponseEntity<ResponseObject> updateUserById(Long id, User newUser)  {
        User user = userRepository.findById(id).orElse(null);
        if(user==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find account with id "+id,null));
        //user.setUserEmail(newUser.getUserEmail());
        user.setUserImage(newUser.getUserImage());
        user.setUserName(newUser.getUserName());
        user.setUserSex(newUser.getUserSex());
        user.setUserDateOfBirth(newUser.getUserDateOfBirth());
        //user.setUserNumberPhone(newUser.getUserNumberPhone());

        User updatedUser = userRepository.save(user);
        if(updatedUser == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"Account update failed ",null));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Account successfully updated ",new UserDTO(user)));

    }

    public ResponseEntity<ResponseObject> getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find account with id "+id,null));

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find successful account with id "+id,new UserDTO(user)));
    }

    public ResponseEntity<ResponseObject> deleteUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find account with id "+id,null));
        userRepository.delete(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful account with id "+id,new UserDTO(user)));
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username.toString());
        }
        return new CustomUserDetails(user);
    }


}
