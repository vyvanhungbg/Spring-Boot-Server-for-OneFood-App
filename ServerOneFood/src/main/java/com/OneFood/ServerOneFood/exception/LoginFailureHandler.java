package com.OneFood.ServerOneFood.exception;

import com.OneFood.ServerOneFood.model.User;
import com.OneFood.ServerOneFood.reponsitory.UserRepository;
import com.OneFood.ServerOneFood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws  ServletException, IOException {

        String email = request.getParameter("email");
        System.out.println("onAuthenticationFailure email: " + email);
        request.setAttribute("email", email);

        String redirectURL = "/login?error&email=" + email;

        if (exception.getMessage().contains("OTP")) {
            redirectURL = "/login?otp=true&email=" + email;
        } else {
            User user = userRepository.findByUserEmail(email);
            if (user.isOTPRequired()) {
                redirectURL = "/login?otp=true&email=" + email;
            }
        }

        super.setDefaultFailureUrl(redirectURL);
        super.onAuthenticationFailure(request, response, exception);
    }

}
