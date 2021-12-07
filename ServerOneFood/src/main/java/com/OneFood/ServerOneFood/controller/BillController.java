package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorAccessDeniedException;
import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.Bill;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.service.BillService;
import com.OneFood.ServerOneFood.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("one-app/v1/bill")
@PreAuthorize("isAuthenticated()")
public class BillController {
    @Autowired
    private final BillService billService;
    @Autowired
    private final MyService myService;
    public BillController(BillService billService, MyService myService) {
        this.billService = billService;
        this.myService = myService;
    }

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllBill(){
        return billService.getAllBill();
    }

    @GetMapping("/{id}")
    @PostAuthorize("@myService.castResponseObjectToBill(returnObject.body).idUser == authentication.principal.user.idUser or hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> getBillById(@PathVariable Long id) throws ErrorNotFoundException, ErrorAccessDeniedException {
        return billService.getBillById(id);
    }

    @PostMapping("")
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@Valid @RequestBody Bill bill) throws ErrorExecutionFailedException {
        return billService.addNewBill(bill);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateBillsById(@PathVariable(value = "id") Long id, @Valid @RequestBody Bill newBill) throws ErrorNotFoundException, ErrorExecutionFailedException, ErrorAccessDeniedException {
        return billService.updateBillById(id,newBill);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteBillsById(@PathVariable(value = "id") Long id) throws ErrorNotFoundException, ErrorExecutionFailedException {
        return billService.deleteBillById(id);
    }


}
