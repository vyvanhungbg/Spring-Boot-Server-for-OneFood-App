package com.OneFood.ServerOneFood.model;



import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;
    @Column(unique = true,nullable = false)
    @NotNull(message = "This field can not be null")
    private String userName;
    @NotNull(message = "This field can not be null")
    private String userPassword;
    private String userImage;
    private String userDateOfBirth;
    private String userSex;
    private String userMoney;
    @Column(unique = true,nullable = false)
    @NotNull(message = "This field can not be null")
    private String userNumberPhone;
    @Column(unique = true,nullable = false)
    @NotNull(message = "This field can not be null")
    private String userEmail;
    private boolean enable;

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



    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

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

    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }


    @JsonBackReference
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
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
