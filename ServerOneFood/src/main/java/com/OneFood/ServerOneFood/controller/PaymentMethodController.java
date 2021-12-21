package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorAccessDeniedException;
import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.model.PaymentMethod;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.model.SliderItem;
import com.OneFood.ServerOneFood.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("one-app/v1/payment-method")
public class PaymentMethodController {
    @Autowired
    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<ResponseObject> addNewPayment(@Valid @RequestBody PaymentMethod paymentMethod) throws ErrorExecutionFailedException, ErrorAccessDeniedException {
        return paymentMethodService.addNewPaymentMethod(paymentMethod);
    }
}
