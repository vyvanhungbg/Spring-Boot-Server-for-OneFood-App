package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorAccessDeniedException;
import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.model.User;
import com.OneFood.ServerOneFood.service.UserService;
import com.sun.mail.smtp.SMTPAddressFailedException;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("one-app/v1/user")
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<ResponseObject> getInfoUser() throws ErrorAccessDeniedException, ErrorNotFoundException {
        return userService.getInfoUser();
    }

    @PostMapping("")
    ResponseEntity<ResponseObject> addNewUser(@Valid  @RequestBody User user) throws ErrorExecutionFailedException {
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return userService.addNewUser(user);
    }

  /*  @GetMapping("/{test}")
    List<String> test(@PathVariable(value = "test") String test){
        return userService.test(test);
    }*/

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseObject> getUserById(@PathVariable Long id) throws ErrorAccessDeniedException, ErrorNotFoundException {
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
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseObject> updateUserById(@PathVariable(value = "id") Long id,@Valid @RequestBody User newUser) throws ErrorAccessDeniedException, ErrorExecutionFailedException, ErrorNotFoundException {
        return userService.updateUserById(id,newUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> deleteUserById(@PathVariable(value = "id") Long id) throws ErrorAccessDeniedException, ErrorNotFoundException {
        return userService.deleteUserById(id);
    }

    @PostMapping("/check-phone-exists/{phoneNumber}")
    public ResponseEntity<ResponseObject> checkPhoneExists(@PathVariable(value = "phoneNumber") String phoneNumber){
        return userService.checkPhoneExists(phoneNumber);
    }


    @PostMapping("/generate_otp")
    public ResponseEntity<ResponseObject> generateOTP(@RequestParam(value = "email",defaultValue = "") String email) throws ErrorExecutionFailedException, ErrorNotFoundException {
         return userService.generateOneTimePassword(email);

    }

    @PostMapping("/confirm_email")
    public ResponseEntity<ResponseObject> ConfirmEmail(@RequestParam(value = "email",defaultValue = "") String email, @RequestParam(value = "otp",defaultValue = "") String otp) throws ErrorNotFoundException {
        return userService.confirmEmail(email,otp);
    }

}
