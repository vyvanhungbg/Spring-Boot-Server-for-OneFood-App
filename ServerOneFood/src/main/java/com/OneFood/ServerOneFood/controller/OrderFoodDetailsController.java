package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.model.OrderFoodDetails;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.service.OrderFoodDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-food-details")
public class OrderFoodDetailsController {
    @Autowired
    private final OrderFoodDetailsService orderFoodDetailsService;

    public OrderFoodDetailsController(OrderFoodDetailsService orderFoodDetailsService) {
        this.orderFoodDetailsService = orderFoodDetailsService;
    }


    @GetMapping("")
    ResponseEntity<ResponseObject> getAllOrderFoodDetails(){
        return orderFoodDetailsService.getAllOrderFoodDetails();
    }
    @PostMapping("")
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@RequestBody OrderFoodDetails orderFoodDetail){
        return orderFoodDetailsService.addNewOrderFoodDetails(orderFoodDetail);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getOrderFoodDetailssById(@PathVariable Long id){
        return orderFoodDetailsService.getOrderFoodDetailsById(id);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateOrderFoodDetailssById(@PathVariable(value = "id") Long id, @RequestBody OrderFoodDetails newOrderFoodDetails)  {
        return orderFoodDetailsService.updateOrderFoodDetailsById(id,newOrderFoodDetails);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteOrderFoodDetailssById(@PathVariable(value = "id") Long id){
        return orderFoodDetailsService.deleteOrderFoodDetailsById(id);
    }
}
