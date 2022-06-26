package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.model.User;
import com.OneFood.ServerOneFood.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("")
public class ForgetPasswordController {
    @Autowired
    private final UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    public ForgetPasswordController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/one-app/v1/forgot_password")
    public ResponseEntity<ResponseObject> forgotPassword(HttpServletRequest request, @RequestParam(value = "email", defaultValue = "") String email) throws ErrorNotFoundException {

        String token = RandomString.make(45);
        userService.updateResetPassword(token, email);
        String passwordResetLink = getSiteURL(request) + "/reset_password?token=" + token;
        try{
            sendEmail(email, passwordResetLink);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(false,"Can not send message to email "+email, null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,passwordResetLink, null));

    }

    private void sendEmail(String email, String passwordResetLink) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("onefoodteam@gmail.com", "Onefood Support");
        helper.setTo(email);
        helper.setSubject("Here's the link to reset your password");
        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + passwordResetLink + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";


        helper.setText(content, true);

        mailSender.send(message);

    }

    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }


    @RequestMapping(value = "/reset_password", method = RequestMethod.GET)
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User customer = userService.getUserByResetPasswordToken(token);
        model.addAttribute("token", token);

        if (customer == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }

        return "reset_password_form";
    }


    @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.getUserByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        } else {
            userService.updatePassword(user, password);

            model.addAttribute("message", "You have successfully changed your password.");
        }

        return "message";
    }
}
