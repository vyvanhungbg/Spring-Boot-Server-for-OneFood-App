package com.OneFood.ServerOneFood.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "type_of_discount")
public class TypeOfDiscountCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTypeOfDiscountCode;
    @NotNull(message = "This field can not be null")
    private String typeOfDiscountCodeName;
    private Long idPaymentMethod;

    @OneToMany(targetEntity = FoodDiscountCode.class)
    @JoinColumn(name = "idTypeOfDiscountCode", referencedColumnName = "idTypeOfDiscountCode")
    private List<FoodDiscountCode> foodDiscountCodes;

    public TypeOfDiscountCode(String typeOfDiscountCodeName, Long idPaymentMethod, List<FoodDiscountCode> foodDiscountCodes) {
        this.typeOfDiscountCodeName = typeOfDiscountCodeName;
        this.idPaymentMethod = idPaymentMethod;
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

    public Long getIdPaymentMethod() {
        return idPaymentMethod;
    }

    public void setIdPaymentMethod(Long idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }
}
