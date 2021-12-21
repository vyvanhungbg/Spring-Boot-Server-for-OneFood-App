package com.OneFood.ServerOneFood.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPaymentMethod;
    String paymentMethodName;

    @OneToMany(targetEntity = TypeOfDiscountCode.class)
    @JoinColumn(name = "idPaymentMethod", referencedColumnName = "idPaymentMethod")
    private List<TypeOfDiscountCode> typeOfDiscountCodes;


    public PaymentMethod() {
    }

    public PaymentMethod(String paymentMethodName, List<TypeOfDiscountCode> typeOfDiscountCodes) {
        this.paymentMethodName = paymentMethodName;
        this.typeOfDiscountCodes = typeOfDiscountCodes;
    }

    public List<TypeOfDiscountCode> getTypeOfDiscountCodes() {
        return typeOfDiscountCodes;
    }

    public void setTypeOfDiscountCodes(List<TypeOfDiscountCode> typeOfDiscountCodes) {
        this.typeOfDiscountCodes = typeOfDiscountCodes;
    }

    public long getIdPaymentMethod() {
        return idPaymentMethod;
    }

    public void setIdPaymentMethod(long idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }
}
