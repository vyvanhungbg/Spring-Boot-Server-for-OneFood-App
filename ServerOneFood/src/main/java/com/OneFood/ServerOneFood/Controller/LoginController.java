package com.OneFood.ServerOneFood.Controller;

import com.OneFood.ServerOneFood.DTO.LoginEntity;
import com.OneFood.ServerOneFood.Jwt.JwtTokenProvider;
import com.OneFood.ServerOneFood.Model.CustomUserDetails;
import com.OneFood.ServerOneFood.Model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
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
                           loginRequest.getUserName(),
                           loginRequest.getUserPassword()
                   )
           );
           SecurityContextHolder.getContext().setAuthentication(authentication);

           // Trả về jwt cho người dùng.
           String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
           return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Successful ", jwt));

       }catch (BadCredentialsException e){
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(false,"Failed ", null));
       }

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
         }

    // Api /api/random yêu cầu phải xác thực mới có thể request
    /*@GetMapping("/random")
    public RandomStuff randomStuff(){
        return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
    }*/

}
