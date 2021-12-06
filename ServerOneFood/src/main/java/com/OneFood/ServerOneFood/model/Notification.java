package com.OneFood.ServerOneFood.model;

import javax.persistence.*;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idNotification;
    private long idBill;
    private long idUser;
    private String notificationContent;
    private String notificationTime;
    private String notificationStatus;
    private String notificationImage;
    private String notificationTitle;

    public Notification(long idBill, long idUser, String notificationContent, String notificationTime, String notificationStatus, String notificationImage, String notificationTitle) {
        this.idBill = idBill;
        this.idUser = idUser;
        this.notificationContent = notificationContent;
        this.notificationTime = notificationTime;
        this.notificationStatus = notificationStatus;
        this.notificationImage = notificationImage;
        this.notificationTitle = notificationTitle;
    }

    public Notification() {
    }

    public long getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(long idNotification) {
        this.idNotification = idNotification;
    }

    public long getIdBill() {
        return idBill;
    }

    public void setIdBill(long idBill) {
        this.idBill = idBill;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }



    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }

    public String getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(String notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public String getNotificationImage() {
        return notificationImage;
    }

    public void setNotificationImage(String notificationImage) {
        this.notificationImage = notificationImage;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }
}
