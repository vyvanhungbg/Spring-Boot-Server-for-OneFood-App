package com.OneFood.ServerOneFood.Controller;

import com.OneFood.ServerOneFood.Model.Bill;
import com.OneFood.ServerOneFood.Model.ResponseObject;
import com.OneFood.ServerOneFood.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }


    @GetMapping("")
    ResponseEntity<ResponseObject> getAllBill(){
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


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteBillsById(@PathVariable(value = "id") Long id){
        return billService.deleteBillById(id);
    }
}
