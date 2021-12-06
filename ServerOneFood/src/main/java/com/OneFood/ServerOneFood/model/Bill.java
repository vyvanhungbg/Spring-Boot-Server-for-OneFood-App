package com.OneFood.ServerOneFood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class Bill {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long idBill;
   @Column(nullable = false)
   private long idUser;
   @Column(nullable = false)
   private long idStore;
   @Column(nullable = false)
   private long idLocationOfUser;
   private String billDiscountAmountFromDiscountCode;
   private String billDiscountAmountFromCodeFreeShip;
   private String billMoneyOfShip;

   private String billTotalMoney;
   private String billMoneyToBePaid;

   private String billPayMethod;
   private int    billStatus; // 0 destroy , 1 pending , 2 confirm , 3 done

   private String billTimeOrder;
   private String billTimeConfirm;
   private String billTimeDone;
   private String billTimeDestroy;
   private String billNote;


   @JsonIgnore
   @OneToMany(targetEntity = OrderFoodDetails.class)
   @JoinColumn(name = "idBill", referencedColumnName = "idBill")
   private List<OrderFoodDetails> orderFoodDetails;

   public Bill() {
   }



   public Bill(long idUser, long idStore, long idLocationOfUser, String billDiscountAmountFromDiscountCode, String billDiscountAmountFromCodeFreeShip, String billMoneyOfShip, String billTotalMoney, String billMoneyToBePaid, String billPayMethod, int billStatus, String billTimeOrder, String billTimeConfirm, String billTimeDone, String billTimeDestroy, String billNote, List<OrderFoodDetails> orderFoodDetails) {
      this.idUser = idUser;
      this.idStore = idStore;
      this.idLocationOfUser = idLocationOfUser;
      this.billDiscountAmountFromDiscountCode = billDiscountAmountFromDiscountCode;
      this.billDiscountAmountFromCodeFreeShip = billDiscountAmountFromCodeFreeShip;
      this.billMoneyOfShip = billMoneyOfShip;
      this.billTotalMoney = billTotalMoney;
      this.billMoneyToBePaid = billMoneyToBePaid;
      this.billPayMethod = billPayMethod;
      this.billStatus = billStatus;
      this.billTimeOrder = billTimeOrder;
      this.billTimeConfirm = billTimeConfirm;
      this.billTimeDone = billTimeDone;
      this.billTimeDestroy = billTimeDestroy;
      this.billNote = billNote;
      this.orderFoodDetails = orderFoodDetails;
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

   public long getIdStore() {
      return idStore;
   }

   public void setIdStore(long idStore) {
      this.idStore = idStore;
   }

   public long getIdLocationOfUser() {
      return idLocationOfUser;
   }

   public void setIdLocationOfUser(long idLocationOfUser) {
      this.idLocationOfUser = idLocationOfUser;
   }

   public String getBillDiscountAmountFromDiscountCode() {
      return billDiscountAmountFromDiscountCode;
   }

   public void setBillDiscountAmountFromDiscountCode(String billDiscountAmountFromDiscountCode) {
      this.billDiscountAmountFromDiscountCode = billDiscountAmountFromDiscountCode;
   }

   public String getBillDiscountAmountFromCodeFreeShip() {
      return billDiscountAmountFromCodeFreeShip;
   }

   public void setBillDiscountAmountFromCodeFreeShip(String billDiscountAmountFromCodeFreeShip) {
      this.billDiscountAmountFromCodeFreeShip = billDiscountAmountFromCodeFreeShip;
   }

   public String getBillMoneyOfShip() {
      return billMoneyOfShip;
   }

   public void setBillMoneyOfShip(String billMoneyOfShip) {
      this.billMoneyOfShip = billMoneyOfShip;
   }

   public String getBillTotalMoney() {
      return billTotalMoney;
   }

   public void setBillTotalMoney(String billTotalMoney) {
      this.billTotalMoney = billTotalMoney;
   }

   public String getBillMoneyToBePaid() {
      return billMoneyToBePaid;
   }

   public void setBillMoneyToBePaid(String billMoneyToBePaid) {
      this.billMoneyToBePaid = billMoneyToBePaid;
   }

   public String getBillPayMethod() {
      return billPayMethod;
   }

   public void setBillPayMethod(String billPayMethod) {
      this.billPayMethod = billPayMethod;
   }

   public int getBillStatus() {
      return billStatus;
   }

   public void setBillStatus(int billStatus) {
      this.billStatus = billStatus;
   }

   public String getBillTimeOrder() {
      return billTimeOrder;
   }

   public void setBillTimeOrder(String billTimeOrder) {
      this.billTimeOrder = billTimeOrder;
   }

   public String getBillTimeConfirm() {
      return billTimeConfirm;
   }

   public void setBillTimeConfirm(String billTimeConfirm) {
      this.billTimeConfirm = billTimeConfirm;
   }

   public String getBillTimeDone() {
      return billTimeDone;
   }

   public void setBillTimeDone(String billTimeDone) {
      this.billTimeDone = billTimeDone;
   }

   public String getBillTimeDestroy() {
      return billTimeDestroy;
   }

   public void setBillTimeDestroy(String billTimeDestroy) {
      this.billTimeDestroy = billTimeDestroy;
   }

   public String getBillNote() {
      return billNote;
   }

   public void setBillNote(String billNote) {
      this.billNote = billNote;
   }

   public List<OrderFoodDetails> getOrderFoodDetails() {
      return orderFoodDetails;
   }

   public void setOrderFoodDetails(List<OrderFoodDetails> orderFoodDetails) {
      this.orderFoodDetails = orderFoodDetails;
   }
}
