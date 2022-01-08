package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.service.MoneyOfShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("one-app/v1/money-of-ship")
public class MoneyOfShipController {
    @Autowired
    private final MoneyOfShipService moneyOfShipService;

    public MoneyOfShipController(MoneyOfShipService moneyOfShipService) {
        this.moneyOfShipService = moneyOfShipService;
    }

    @GetMapping("/{distance}")
    ResponseEntity<ResponseObject> getAllBill(@PathVariable(value = "distance") String distance) throws ErrorExecutionFailedException, ErrorNotFoundException {
        return moneyOfShipService.getPriceByDistance(distance);
    }
}
