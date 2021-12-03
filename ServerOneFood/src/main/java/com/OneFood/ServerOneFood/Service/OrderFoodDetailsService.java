package com.OneFood.ServerOneFood.Service;

import com.OneFood.ServerOneFood.Model.OrderFoodDetails;
import com.OneFood.ServerOneFood.Model.ResponseObject;
import com.OneFood.ServerOneFood.Reponsitory.OrderFoodDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFoodDetailsService {
    @Autowired
    private final OrderFoodDetailsRepository orderFoodDetailsRepository;

    public OrderFoodDetailsService(OrderFoodDetailsRepository orderFoodDetailsRepository) {
        this.orderFoodDetailsRepository = orderFoodDetailsRepository;
    }


    public ResponseEntity<ResponseObject> getAllOrderFoodDetails(){
        List<OrderFoodDetails> orderFoodDetails =  orderFoodDetailsRepository.findAll();
        if(orderFoodDetails.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty orderFoodDetails list ", orderFoodDetails));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find all successful orderFoodDetails ", orderFoodDetails));

    }

    public ResponseEntity<ResponseObject> addNewOrderFoodDetails(OrderFoodDetails newOrderFoodDetails){
        OrderFoodDetails orderFoodDetails = orderFoodDetailsRepository.save(newOrderFoodDetails);
        if(orderFoodDetails == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"New orderFoodDetails create failed ",orderFoodDetails));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New orderFoodDetails successfully created ",orderFoodDetails));

    }

    public ResponseEntity<ResponseObject> updateOrderFoodDetailsById(Long id, OrderFoodDetails newOrderFoodDetails)  {
        OrderFoodDetails orderFoodDetails = orderFoodDetailsRepository.findById(id).orElse(null);
        if(orderFoodDetails==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find orderFoodDetails with id "+id,null));
        orderFoodDetails.setFoodPrice(newOrderFoodDetails.getFoodPrice());
        orderFoodDetails.setNumberOfFood(newOrderFoodDetails.getNumberOfFood());

        OrderFoodDetails updatedOrderFoodDetails = orderFoodDetailsRepository.save(orderFoodDetails);
        if(updatedOrderFoodDetails == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"OrderFoodDetails update failed ",updatedOrderFoodDetails));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"OrderFoodDetails successfully updated ",updatedOrderFoodDetails));

    }

    public ResponseEntity<ResponseObject> getOrderFoodDetailsById(Long id) {
        OrderFoodDetails orderFoodDetails = orderFoodDetailsRepository.findById(id).orElse(null);
        if(orderFoodDetails==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find orderFoodDetails with id "+id,null));

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find successful orderFoodDetails with id "+id,orderFoodDetails));
    }

    public ResponseEntity<ResponseObject> deleteOrderFoodDetailsById(Long id) {
        OrderFoodDetails orderFoodDetails = orderFoodDetailsRepository.findById(id).orElse(null);
        if(orderFoodDetails==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find orderFoodDetails with id "+id,null));
        orderFoodDetailsRepository.delete(orderFoodDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful orderFoodDetails with id "+id,orderFoodDetails));
    }
}
