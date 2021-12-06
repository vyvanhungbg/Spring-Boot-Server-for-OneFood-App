package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.model.User;
import com.OneFood.ServerOneFood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("")
    ResponseEntity<ResponseObject> getAllTypeOfDiscountCode(){
        return userService.getAllUser();
    }
    @PostMapping("")
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@RequestBody User user){
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return userService.addNewUser(user);
    }

  /*  @GetMapping("/{test}")
    List<String> test(@PathVariable(value = "test") String test){
        return userService.test(test);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/add-role/{id}/{role}")
    public ResponseEntity<ResponseObject> addUserRoleByIdUser(@PathVariable(value = "id") Long id, @PathVariable(value = "role") String newRole) throws ErrorExecutionFailedException, ErrorNotFoundException {
        return userService.addUserRoleByIdUser(id,newRole);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/remove-role/{id}/{role}")
    public ResponseEntity<ResponseObject> removeUserRoleByIdUser(@PathVariable(value = "id") Long id, @PathVariable(value = "role") String newRole) throws ErrorExecutionFailedException, ErrorNotFoundException {
        return userService.removeUserRoleByIdUser(id,newRole);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateUserById(@PathVariable(value = "id") Long id, @RequestBody User newUser)  {
        return userService.updateUserById(id,newUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteUserById(@PathVariable(value = "id") Long id){
        return userService.deleteUserById(id);
    }
}
