package com.OneFood.ServerOneFood.model;

import javax.persistence.*;

@Entity
@Table
public class FoodReviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFoodReviews;
    private long idFood;
    private long idUser;
    private String foodReviewsContent;
    private String foodReviewsImage;
    private String foodReviewsStar;
    private String foodReviewsTime;
    private String foodReviewsStatus;

    public FoodReviews(long idFood, long idUser, String foodReviewsContent, String foodReviewsImage, String foodReviewsStar, String foodReviewsTime, String foodReviewsStatus) {
        this.idFood = idFood;
        this.idUser = idUser;
        this.foodReviewsContent = foodReviewsContent;
        this.foodReviewsImage = foodReviewsImage;
        this.foodReviewsStar = foodReviewsStar;
        this.foodReviewsTime = foodReviewsTime;
        this.foodReviewsStatus = foodReviewsStatus;
    }

    public FoodReviews() {
    }

    public long getIdFoodReviews() {
        return idFoodReviews;
    }

    public void setIdFoodReviews(long idFoodReviews) {
        this.idFoodReviews = idFoodReviews;
    }

    public long getIdFood() {
        return idFood;
    }

    public void setIdFood(long idFood) {
        this.idFood = idFood;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getFoodReviewsContent() {
        return foodReviewsContent;
    }

    public void setFoodReviewsContent(String foodReviewsContent) {
        this.foodReviewsContent = foodReviewsContent;
    }

    public String getFoodReviewsImage() {
        return foodReviewsImage;
    }

    public void setFoodReviewsImage(String foodReviewsImage) {
        this.foodReviewsImage = foodReviewsImage;
    }

    public String getFoodReviewsStar() {
        return foodReviewsStar;
    }

    public void setFoodReviewsStar(String foodReviewsStar) {
        this.foodReviewsStar = foodReviewsStar;
    }

    public String getFoodReviewsTime() {
        return foodReviewsTime;
    }

    public void setFoodReviewsTime(String foodReviewsTime) {
        this.foodReviewsTime = foodReviewsTime;
    }

    public String getFoodReviewsStatus() {
        return foodReviewsStatus;
    }

    public void setFoodReviewsStatus(String foodReviewsStatus) {
        this.foodReviewsStatus = foodReviewsStatus;
    }
}
