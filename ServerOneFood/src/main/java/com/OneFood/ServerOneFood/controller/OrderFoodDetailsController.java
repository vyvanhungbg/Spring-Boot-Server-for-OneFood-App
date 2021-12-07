package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorAccessDeniedException;
import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.OrderFoodDetails;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.service.OrderFoodDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/one-app/v1/order-food-details")
@PreAuthorize("isAuthenticated()")
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
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@Valid @RequestBody OrderFoodDetails orderFoodDetail) throws ErrorExecutionFailedException, ErrorAccessDeniedException {
        return orderFoodDetailsService.addNewOrderFoodDetails(orderFoodDetail);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getOrderFoodDetailsById(@PathVariable Long id) throws ErrorNotFoundException {
        return orderFoodDetailsService.getOrderFoodDetailsById(id);
    }

    @GetMapping("/id-bill/{id}")
    public ResponseEntity<ResponseObject> getOrderFoodDetailsByIdBill(@PathVariable Long id) throws ErrorAccessDeniedException, ErrorExecutionFailedException {
        return orderFoodDetailsService.getOrderFoodDetailsByIdBill(id);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateOrderFoodDetailsById(@PathVariable(value = "id") Long id, @Valid @RequestBody OrderFoodDetails newOrderFoodDetails) throws ErrorExecutionFailedException, ErrorNotFoundException, ErrorAccessDeniedException {
        return orderFoodDetailsService.updateOrderFoodDetailsById(id,newOrderFoodDetails);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteOrderFoodDetailsById(@PathVariable(value = "id") Long id) throws ErrorNotFoundException, ErrorAccessDeniedException, ErrorExecutionFailedException {
        return orderFoodDetailsService.deleteOrderFoodDetailsById(id);
    }
}
