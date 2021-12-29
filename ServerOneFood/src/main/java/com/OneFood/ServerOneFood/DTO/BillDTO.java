package com.OneFood.ServerOneFood.DTO;

import com.OneFood.ServerOneFood.model.Bill;
import com.OneFood.ServerOneFood.model.OrderFoodDetails;

import java.util.List;

public class BillDTO {
    private long idBill;
    private long idUser;
    private long idStore;

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
    private List<FoodInBillDTO> foods;

    public BillDTO(Bill bill, List<FoodInBillDTO> foods) {
        this.idBill = bill.getIdBill();
        this.idUser = bill.getIdUser();
        this.idStore = bill.getIdStore();
        this.idLocationOfUser = bill.getIdLocationOfUser();
        this.billDiscountAmountFromDiscountCode = bill.getBillDiscountAmountFromDiscountCode();
        this.billDiscountAmountFromCodeFreeShip = bill.getBillDiscountAmountFromCodeFreeShip();
        this.billMoneyOfShip = bill.getBillMoneyOfShip();
        this.billTotalMoney = bill.getBillTotalMoney();
        this.billMoneyToBePaid = bill.getBillMoneyToBePaid();
        this.billPayMethod = bill.getBillPayMethod();
        this.billStatus = bill.getBillStatus();
        this.billTimeOrder = bill.getBillTimeOrder();
        this.billTimeConfirm = bill.getBillTimeConfirm();
        this.billTimeDone = bill.getBillTimeDone();
        this.billTimeDestroy = bill.getBillTimeDestroy();
        this.billNote = bill.getBillNote();
        this.foods = foods;
    }

    public BillDTO() {
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

    public List<FoodInBillDTO> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodInBillDTO> foods) {
        this.foods = foods;
    }
}
