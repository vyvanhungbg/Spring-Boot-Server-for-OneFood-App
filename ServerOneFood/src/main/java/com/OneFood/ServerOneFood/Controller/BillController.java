package com.OneFood.ServerOneFood.Controller;

import com.OneFood.ServerOneFood.DTO.BillDTO;
import com.OneFood.ServerOneFood.Model.Bill;
import com.OneFood.ServerOneFood.Model.ResponseObject;
import com.OneFood.ServerOneFood.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("one-app/v1/bill")
@PreAuthorize("isAuthenticated()")
public class BillController {
    @Autowired
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }


    @GetMapping("")
    @PostFilter("filterObject.idUser== authentication.principal.user.idUser")
    @PreAuthorize("hasAuthority('ADMIN')")
    List<BillDTO> getAllBill(){
        return billService.getAllBill();
    }


    @PostMapping("")
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@RequestBody Bill bill){
        return billService.addNewBill(bill);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getBillsById(@PathVariable Long id){
        return billService.getBillById(id);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateBillsById(@PathVariable(value = "id") Long id, @RequestBody Bill newBill)  {
        return billService.updateBillById(id,newBill);
    }



    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<BillDTO> deleteBillsById(@PathVariable(value = "id") Long id){
        return billService.deleteBillById(id);
    }
}
