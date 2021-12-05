package com.OneFood.ServerOneFood.Service;

import com.OneFood.ServerOneFood.DTO.BillDTO;
import com.OneFood.ServerOneFood.Model.Bill;
import com.OneFood.ServerOneFood.Model.ResponseObject;
import com.OneFood.ServerOneFood.Reponsitory.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {
    @Autowired
    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }


    public List<BillDTO> getAllBill(){
        List<Bill> bills =  billRepository.findAll();
        List<BillDTO> billDTOS = new ArrayList<>();
        bills.stream().forEach(bill -> billDTOS.add(new BillDTO(bill)));
        if(bills.isEmpty())
            return  new ArrayList<>(billDTOS);
        return  new ArrayList<>(billDTOS);

    }

    public ResponseEntity<ResponseObject> addNewBill(Bill newBill){
        Bill bill = billRepository.save(newBill);
        if(bill == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"New bill create failed ",bill));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New bill successfully created ",bill));

    }

    public ResponseEntity<ResponseObject> updateBillById(Long id, Bill newBill)  {
        Bill bill = billRepository.findById(id).orElse(null);
        if(bill==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find bill with id "+id,null));
       //bill.setBillMoneyOfShip(newBill.getBillMoneyOfShip());
       //bill.setBillPayMethod(newBill.getBillPayMethod());
       //bill.setBillNote(newBill.getBillNote());
       //bill.setBillTimeOrder(newBill.getBillTimeOrder());
       bill.setBillTimeConfirm(newBill.getBillTimeConfirm());
       bill.setBillTimeDone(newBill.getBillTimeDone());
       bill.setBillTimeDestroy(newBill.getBillTimeDestroy());
       bill.setBillStatus(newBill.getBillStatus());
       //bill.setBillDiscountAmountFromCodeFreeShip(newBill.getBillDiscountAmountFromCodeFreeShip());
      // bill.setBillDiscountAmountFromDiscountCode(newBill.getBillDiscountAmountFromDiscountCode());
       //bill.setBillTotalMoney(newBill.getBillTotalMoney());
       //bill.setBillMoneyToBePaid(newBill.getBillMoneyToBePaid());

        Bill updatedBill = billRepository.save(bill);
        if(updatedBill == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"Bill update failed ",updatedBill));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Bill successfully updated ",updatedBill));

    }

    public ResponseEntity<ResponseObject> getBillById(Long id) {
        Bill bill = billRepository.findById(id).orElse(null);
        if(bill==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find bill with id "+id,null));

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find successful bill with id "+id,bill));
    }

    public ResponseEntity<BillDTO> deleteBillById(Long id) {
        Bill bill = billRepository.findById(id).orElse(null);
        if(bill==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BillDTO());
        billRepository.delete(bill);
        return ResponseEntity.status(HttpStatus.OK).body(new BillDTO(bill));
    }
}
