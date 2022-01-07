package com.OneFood.ServerOneFood.DTO;

import javax.persistence.Entity;


public class LoginEntity {
    private String userEmail;
    private String userPassword;
    private String role;

    public LoginEntity() {
    }

    public LoginEntity(String userEmail, String userPassword, String role) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.role = role;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
