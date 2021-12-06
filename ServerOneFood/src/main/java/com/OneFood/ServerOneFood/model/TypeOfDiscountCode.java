package com.OneFood.ServerOneFood.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "type_of_discount")
public class TypeOfDiscountCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTypeOfDiscountCode;
    private String typeOfDiscountCodeName;

    @OneToMany(targetEntity = FoodDiscountCode.class)
    @JoinColumn(name = "idTypeOfDiscountCode", referencedColumnName = "idTypeOfDiscountCode")
    private List<FoodDiscountCode> foodDiscountCodes;

    public TypeOfDiscountCode(long idTypeOfDiscountCode, String typeOfDiscountCodeName, List<FoodDiscountCode> foodDiscountCodes) {
        this.idTypeOfDiscountCode = idTypeOfDiscountCode;
        this.typeOfDiscountCodeName = typeOfDiscountCodeName;
        this.foodDiscountCodes = foodDiscountCodes;
    }

    public TypeOfDiscountCode() {
    }

    public List<FoodDiscountCode> getFoodDiscountCodes() {
        return foodDiscountCodes;
    }

    public void setFoodDiscountCodes(List<FoodDiscountCode> foodDiscountCodes) {
        this.foodDiscountCodes = foodDiscountCodes;
    }

    public long getIdTypeOfDiscountCode() {
        return idTypeOfDiscountCode;
    }

    public void setIdTypeOfDiscountCode(long idTypeOfDiscountCode) {
        this.idTypeOfDiscountCode = idTypeOfDiscountCode;
    }

    public String getTypeOfDiscountCodeName() {
        return typeOfDiscountCodeName;
    }

    public void setTypeOfDiscountCodeName(String typeOfDiscountCodeName) {
        this.typeOfDiscountCodeName = typeOfDiscountCodeName;
    }
}
