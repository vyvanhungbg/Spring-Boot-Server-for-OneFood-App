package com.OneFood.ServerOneFood.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;

@Embeddable
public class UserDiscountKey implements Serializable {

    @Column(name = "idUser")
    private Long idUser;

    @Column(name = "idFoodDiscountCode")
    private Long idFoodDiscountCode;

    public UserDiscountKey() {
    }

    public UserDiscountKey(Long idUser, Long idFoodDiscountCode) {
        this.idUser = idUser;
        this.idFoodDiscountCode = idFoodDiscountCode;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdFoodDiscountCode() {
        return idFoodDiscountCode;
    }

    public void setIdFoodDiscountCode(Long idFoodDiscountCode) {
        this.idFoodDiscountCode = idFoodDiscountCode;
    }
}
