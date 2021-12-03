package com.OneFood.ServerOneFood.Controller;

import com.OneFood.ServerOneFood.Model.ResponseObject;
import com.OneFood.ServerOneFood.Model.TypeOfDiscountCode;
import com.OneFood.ServerOneFood.Model.User;
import com.OneFood.ServerOneFood.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("")
    ResponseEntity<ResponseObject> getAllTypeOfDiscountCode(){
        return userService.getAllUser();
    }
    @PostMapping("")
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@RequestBody User user){
        return userService.addNewUser(user);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
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
