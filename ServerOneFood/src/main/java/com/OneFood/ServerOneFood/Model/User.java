package com.OneFood.ServerOneFood.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;
    @Column(unique = true,nullable = false)
    private String userName;
    private String userPassword;
    private String userImage;
    private String userDateOfBirth;
    private String userSex;
    private String userMoney;
    @Column(unique = true,nullable = false)
    private String userNumberPhone;
    @Column(unique = true,nullable = false)
    private String userEmail;

    @OneToMany(targetEntity = Cart.class)
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private List<Cart> carts;

    @OneToMany(targetEntity = FoodReviews.class)
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private List<FoodReviews> foodReviews;

    @OneToMany(targetEntity = LocationOfUser.class)
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private List<LocationOfUser> locationOfUsers;

    @OneToMany(targetEntity = Notification.class)
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private List<Notification> notifications;

    @OneToMany(targetEntity = Bill.class)
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private List<Bill> bills;
    public User() {
    }

    public User(String userName, String userPassword, String userImage, String userDateOfBirth, String userSex, String userMoney, String userNumberPhone, String userEmail, List<Cart> carts, List<FoodReviews> foodReviews, List<LocationOfUser> locationOfUsers, List<Notification> notifications, List<Bill> bills) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userImage = userImage;
        this.userDateOfBirth = userDateOfBirth;
        this.userSex = userSex;
        this.userMoney = userMoney;
        this.userNumberPhone = userNumberPhone;
        this.userEmail = userEmail;
        this.carts = carts;
        this.foodReviews = foodReviews;
        this.locationOfUsers = locationOfUsers;
        this.notifications = notifications;
        this.bills = bills;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<LocationOfUser> getLocationOfUsers() {
        return locationOfUsers;
    }

    public void setLocationOfUsers(List<LocationOfUser> locationOfUsers) {
        this.locationOfUsers = locationOfUsers;
    }

    public List<FoodReviews> getFoodReviews() {
        return foodReviews;
    }

    public void setFoodReviews(List<FoodReviews> foodReviews) {
        this.foodReviews = foodReviews;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserDateOfBirth() {
        return userDateOfBirth;
    }

    public void setUserDateOfBirth(String userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(String userMoney) {
        this.userMoney = userMoney;
    }

    public String getUserNumberPhone() {
        return userNumberPhone;
    }

    public void setUserNumberPhone(String userNumberPhone) {
        this.userNumberPhone = userNumberPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}
