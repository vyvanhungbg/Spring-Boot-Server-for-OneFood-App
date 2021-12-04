package com.OneFood.ServerOneFood.Model;

import javax.persistence.*;
import java.util.List;


// Tạo bảng cho loại thực phẩm , đồ ăn , đồ uống
@Table(name = "type_of_food")
@Entity
public class TypeOfFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTypeOfFood;

    @Column()
    private String typeOfFoodName;
    @Column()
    private String typeOfFoodImage;

    @OneToMany(targetEntity = Food.class)
    @JoinColumn(name = "idTypeOfFood", referencedColumnName = "idTypeOfFood")
    private List<Food> foods;

    public TypeOfFood() {
    }

    public TypeOfFood( String typeOfFoodName, String TypeOfFoodImage, List<Food> foods) {

        this.typeOfFoodName = typeOfFoodName;
        this.typeOfFoodImage = TypeOfFoodImage;
        this.foods = foods;
    }

    public long getIdTypeOfFood() {
        return idTypeOfFood;
    }

    public void setIdTypeOfFood(long idTypeOfFood) {
        this.idTypeOfFood = idTypeOfFood;
    }

    public String getTypeOfFoodName() {
        return typeOfFoodName;
    }

    public void setTypeOfFoodName(String typeOfFoodName) {
        this.typeOfFoodName = typeOfFoodName;
    }

    public String getTypeOfFoodImage() {
        return typeOfFoodImage;
    }

    public void setTypeOfFoodImage(String typeOfFoodImage) {
        this.typeOfFoodImage = typeOfFoodImage;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
