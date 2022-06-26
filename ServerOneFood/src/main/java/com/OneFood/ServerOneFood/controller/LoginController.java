package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.DTO.LoginEntity;
import com.OneFood.ServerOneFood.JWT.JwtTokenProvider;
import com.OneFood.ServerOneFood.model.CustomUserDetails;
import com.OneFood.ServerOneFood.model.ResponseObject;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("one-app/v1/login")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;


    @GetMapping("")
    String  getAllBill(){
        return "Heloo fake";
    }

    @PostMapping("")
    public ResponseEntity<ResponseObject> authenticateUser(@RequestBody LoginEntity loginRequest) {

        // Xác thực từ username và password.
       try {
           Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           loginRequest.getUserEmail(),
                           loginRequest.getUserPassword()
                   )
           );
           SecurityContextHolder.getContext().setAuthentication(authentication);
           String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
           return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Successful ", jwt));

       }catch (BadCredentialsException e){
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(false,"Incorrect account or password  ", null));
       }


    }

    @GetMapping("/check_login")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseObject> checkJwtLive(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Token is good ", null));
    }




}
