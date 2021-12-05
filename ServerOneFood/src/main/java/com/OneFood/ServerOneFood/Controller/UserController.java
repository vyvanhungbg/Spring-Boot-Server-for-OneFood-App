package com.OneFood.ServerOneFood.Controller;

import com.OneFood.ServerOneFood.Model.ResponseObject;
import com.OneFood.ServerOneFood.Model.User;
import com.OneFood.ServerOneFood.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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



    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateUserById(@PathVariable(value = "id") Long id, @RequestBody User newUser)  {
        return userService.updateUserById(id,newUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteUserById(@PathVariable(value = "id") Long id){
        return userService.deleteUserById(id);
    }
}
