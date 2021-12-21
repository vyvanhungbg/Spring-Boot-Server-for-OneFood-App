package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.exception.ErrorAccessDeniedException;
import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.model.OrderFoodDetails;
import com.OneFood.ServerOneFood.model.PaymentMethod;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.reponsitory.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodService {
    @Autowired
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public ResponseEntity<ResponseObject> addNewPaymentMethod(PaymentMethod paymentMethod) throws ErrorExecutionFailedException, ErrorAccessDeniedException {

        if(paymentMethodRepository.existsById(paymentMethod.getIdPaymentMethod()))
            throw new ErrorExecutionFailedException("New payment method  create failed Because this item already exists");

        PaymentMethod paymentMethodSaved = paymentMethodRepository.save(paymentMethod);
        if(paymentMethodSaved == null)
            throw new ErrorExecutionFailedException("New payment method create failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New payment method successfully created ",paymentMethod));

    }
}
