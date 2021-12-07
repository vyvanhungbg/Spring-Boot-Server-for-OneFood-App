package com.OneFood.ServerOneFood.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class LocationOfUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLocationOfUser;
    private long idUser;
    @NotNull(message = "This field can not be null")
    private String nameOfLocation;
    @NotNull(message = "This field can not be null")
    private String longitude;
    @NotNull(message = "This field can not be null")
    private String latitude;
    @NotNull(message = "This field can not be null")
    private String nameOfReceiver;
    private boolean isDefaultLocation;  // chỉ set 1 trường true
    @NotNull(message = "This field can not be null")
    private String numberPhoneOfReceiver;
    private boolean isDeleteLocation = false;


    public LocationOfUser(long idUser, String nameOfLocation, String longitude, String latitude, String nameOfReceiver, String numberPhoneOfReceiver) {
        this.idUser = idUser;
        this.nameOfLocation = nameOfLocation;
        this.longitude = longitude;
        this.latitude = latitude;
        this.nameOfReceiver = nameOfReceiver;
        this.numberPhoneOfReceiver = numberPhoneOfReceiver;
    }

    public LocationOfUser() {
    }

    public long getIdLocationOfUser() {
        return idLocationOfUser;
    }

    public void setIdLocationOfUser(long idLocationOfUser) {
        this.idLocationOfUser = idLocationOfUser;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getNameOfLocation() {
        return nameOfLocation;
    }

    public void setNameOfLocation(String nameOfLocation) {
        this.nameOfLocation = nameOfLocation;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getNameOfReceiver() {
        return nameOfReceiver;
    }

    public void setNameOfReceiver(String nameOfReceiver) {
        this.nameOfReceiver = nameOfReceiver;
    }

    public boolean isDefaultLocation() {
        return isDefaultLocation;
    }

    public void setDefaultLocation(boolean defaultLocation) {
        isDefaultLocation = defaultLocation;
    }

    public String getNumberPhoneOfReceiver() {
        return numberPhoneOfReceiver;
    }

    public void setNumberPhoneOfReceiver(String numberPhoneOfReceiver) {
        this.numberPhoneOfReceiver = numberPhoneOfReceiver;
    }
}
