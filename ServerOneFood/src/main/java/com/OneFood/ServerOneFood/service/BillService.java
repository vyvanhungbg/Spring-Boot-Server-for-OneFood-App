package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.Bill;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.reponsitory.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {
    @Autowired
    private final BillRepository billRepository;

    @Autowired
    private final MyService myService;
    public BillService(BillRepository billRepository, MyService myService) {
        this.billRepository = billRepository;
        this.myService = myService;
    }


    public ResponseEntity<ResponseObject> getAllBill(){
        List<Bill> bills =  billRepository.findAll();
        List<Bill> billFilter = new ArrayList<>(bills);
        Long idUser = myService.getPrincipal();
        if(!myService.isRoleAdmin())
             billFilter = bills.stream().filter(bill -> bill.getIdUser() == idUser).collect(Collectors.toList());
        if(bills.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty bill list ", billFilter));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find "+billFilter.size()+" bill successful ", billFilter));

    }

    public ResponseEntity<ResponseObject> addNewBill(Bill newBill) throws ErrorExecutionFailedException {
        Long idUser = myService.getPrincipal();
        newBill.setIdUser(idUser);
        Bill bill = billRepository.save(newBill);
        if(bill == null)
            throw new ErrorExecutionFailedException("New bill create failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New bill successfully created ",bill));

    }

    public ResponseEntity<ResponseObject> updateBillById(Long idBill, Bill newBill) throws ErrorNotFoundException, ErrorExecutionFailedException {
        Bill bill = billRepository.findById(idBill).orElseThrow(() ->  new ErrorNotFoundException("Cannot find bill with id "+idBill));
        Long idUser = myService.getPrincipal();
        if(bill.getIdUser() != idUser && !myService.isRoleAdmin()){
           throw new AccessDeniedException("Access is denied");
        }
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
            throw new ErrorExecutionFailedException("Bill update failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Bill successfully updated ",updatedBill));

    }

    public ResponseEntity<ResponseObject> getBillById(Long id) throws ErrorNotFoundException {
        Bill bill = billRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find bill with id "+id));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find successful bill with id "+id,bill));
    }


    public ResponseEntity<ResponseObject> deleteBillById(Long id) throws ErrorNotFoundException, ErrorExecutionFailedException {
        Bill bill = billRepository.findById(id).orElseThrow(() ->  new ErrorNotFoundException("Cannot find bill with id "+id));
        billRepository.delete(bill);
        if(billRepository.existsById(id))
            throw new ErrorExecutionFailedException("Delete bill failed with id "+id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful bill with id "+id,bill));
    }
}
