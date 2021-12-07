package com.OneFood.ServerOneFood.DTO;

import com.OneFood.ServerOneFood.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDTO {

    private long idUser;
    private String userName;
    private String userImage;
    private String userDateOfBirth;
    private String userSex;
    private String userMoney;
    private String userNumberPhone;
    private String userEmail;

    private List<Cart> carts;

    private List<FoodReviews> foodReviews;

    private List<LocationOfUser> locationOfUsers;

    private List<Notification> notifications;

    private List<Bill> bills;
    private Set<String> roles = new HashSet<>();

    public UserDTO(User user) {
        this.idUser = user.getIdUser();
        this.userName = user.getUserName();
        this.userImage = user.getUserImage();
        this.userDateOfBirth = user.getUserDateOfBirth();
        this.userSex = user.getUserSex();
        this.userMoney = user.getUserMoney();
        this.userNumberPhone = user.getUserNumberPhone();
        this.userEmail = user.getUserEmail();
        this.carts = user.getCarts();
        this.foodReviews = user.getFoodReviews();
        this.locationOfUsers = user.getLocationOfUsers();
        this.notifications = user.getNotifications();
        this.bills = user.getBills();
        this.roles = getRoles(user);
    }
    public Set<String> getRoles(User user){
        List<String> strings = new ArrayList<>();
        for(Role role : user.getRoles()){
            strings.add(role.getRoleName());
        }
        return new HashSet<>(strings);
    }

    public UserDTO() {
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

    public List<FoodReviews> getFoodReviews() {
        return foodReviews;
    }

    public void setFoodReviews(List<FoodReviews> foodReviews) {
        this.foodReviews = foodReviews;
    }

    public List<LocationOfUser> getLocationOfUsers() {
        return locationOfUsers;
    }

    public void setLocationOfUsers(List<LocationOfUser> locationOfUsers) {
        this.locationOfUsers = locationOfUsers;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public Set<String> getRoles() {
        return roles;
    }


}
