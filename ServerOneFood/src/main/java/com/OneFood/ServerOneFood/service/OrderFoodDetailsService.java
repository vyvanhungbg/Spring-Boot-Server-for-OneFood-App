package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.exception.ErrorAccessDeniedException;
import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.OrderFoodDetails;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.reponsitory.BillRepository;
import com.OneFood.ServerOneFood.reponsitory.OrderFoodDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFoodDetailsService {
    @Autowired
    private final OrderFoodDetailsRepository orderFoodDetailsRepository;

    @Autowired
    private  final MyService myService;

    @Autowired
    private final BillRepository billRepository;

    public OrderFoodDetailsService(OrderFoodDetailsRepository orderFoodDetailsRepository, MyService myService, BillRepository billRepository) {
        this.orderFoodDetailsRepository = orderFoodDetailsRepository;
        this.myService = myService;
        this.billRepository = billRepository;
    }


    public ResponseEntity<ResponseObject> getAllOrderFoodDetails(){
        List<OrderFoodDetails> orderFoodDetails =  orderFoodDetailsRepository.findAll();
        if(orderFoodDetails.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty orderFoodDetails list ", orderFoodDetails));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find  "+orderFoodDetails.size()+" orderFoodDetails ", orderFoodDetails));

    }

    public ResponseEntity<ResponseObject> addNewOrderFoodDetails(OrderFoodDetails newOrderFoodDetails) throws ErrorExecutionFailedException, ErrorAccessDeniedException {
        checkPermission(newOrderFoodDetails);
        if(orderFoodDetailsRepository.existsById(newOrderFoodDetails.getIdOrderFoodDetails()))
            throw new ErrorExecutionFailedException("New oder food detail create failed Because this item already exists");

        OrderFoodDetails orderFoodDetails = orderFoodDetailsRepository.save(newOrderFoodDetails);
        if(orderFoodDetails == null)
            throw new ErrorExecutionFailedException("New orderFoodDetails create failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New orderFoodDetails successfully created ",orderFoodDetails));

    }

    public ResponseEntity<ResponseObject> updateOrderFoodDetailsById(Long id, OrderFoodDetails newOrderFoodDetails) throws ErrorNotFoundException, ErrorExecutionFailedException, ErrorAccessDeniedException {
        checkPermission(newOrderFoodDetails);
        OrderFoodDetails orderFoodDetails = orderFoodDetailsRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find orderFoodDetails with id "+id));
        try {
            if(newOrderFoodDetails.getNumberOfFood()==0){
                deleteOrderFoodDetailsById(id);
            }
        }catch (Exception e){
            throw new ErrorExecutionFailedException(e.getMessage());
        }
        orderFoodDetails.setFoodPrice(newOrderFoodDetails.getFoodPrice());
        orderFoodDetails.setNumberOfFood(newOrderFoodDetails.getNumberOfFood());

        OrderFoodDetails updatedOrderFoodDetails = orderFoodDetailsRepository.save(orderFoodDetails);
        if(updatedOrderFoodDetails == null)
            throw new ErrorExecutionFailedException("OrderFoodDetails update failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"OrderFoodDetails successfully updated ",updatedOrderFoodDetails));

    }

    public ResponseEntity<ResponseObject> getOrderFoodDetailsById(Long id) throws ErrorNotFoundException {
        OrderFoodDetails orderFoodDetails = orderFoodDetailsRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find orderFoodDetails with id "+id));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find successful orderFoodDetails with id "+id,orderFoodDetails));
    }

    public ResponseEntity<ResponseObject> deleteOrderFoodDetailsById(Long id) throws ErrorNotFoundException, ErrorAccessDeniedException, ErrorExecutionFailedException {
        OrderFoodDetails orderFoodDetails = orderFoodDetailsRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find orderFoodDetails with id "+id));
        checkPermission(orderFoodDetails);
        orderFoodDetailsRepository.delete(orderFoodDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful orderFoodDetails with id "+id,orderFoodDetails));
    }

    public ResponseEntity<ResponseObject> getOrderFoodDetailsByIdBill(Long idBill) throws ErrorAccessDeniedException, ErrorExecutionFailedException {
        List<OrderFoodDetails> orderFoodDetails = orderFoodDetailsRepository.findByIdBill(idBill);
        if(!myService.isRoleAdmin()){
            Long idUser = myService.getPrincipal();
            Long idUserFindByBill = billRepository.findById(idBill).orElseThrow(() -> new ErrorExecutionFailedException("This bill does not exist")).getIdUser();
            if(idUser != idUserFindByBill)
                throw new ErrorAccessDeniedException("Access is denied");
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find successful orderFoodDetails with id bill "+idBill,orderFoodDetails));
    }

    public void checkPermission(OrderFoodDetails newOrderFoodDetails) throws ErrorExecutionFailedException, ErrorAccessDeniedException {
        if(!myService.isRoleAdmin()){
            Long idUser = myService.getPrincipal();
            Long idUserFindByBill = billRepository.findById(newOrderFoodDetails.getIdBill()).orElseThrow(() -> new ErrorExecutionFailedException("This bill does not exist")).getIdUser();
            if(idUser != idUserFindByBill)
                throw new ErrorAccessDeniedException("Access is denied");
        }
    }

}
