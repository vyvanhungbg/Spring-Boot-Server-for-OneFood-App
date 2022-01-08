package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.Food;
import com.OneFood.ServerOneFood.model.MoneyOfShip;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.reponsitory.MoneyOfShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MoneyOfShipService {
    @Autowired
    private final MoneyOfShipRepository moneyOfShipRepository;

    public MoneyOfShipService(MoneyOfShipRepository moneyOfShipRepository) {
        this.moneyOfShipRepository = moneyOfShipRepository;
    }

    public ResponseEntity<ResponseObject> getPriceByDistance(String distance) throws ErrorNotFoundException, ErrorExecutionFailedException {
        Double mDistance = 0D ;
        try{
            mDistance =  Double.parseDouble(distance);
        }catch (Exception e){
            throw new ErrorExecutionFailedException("The distance to be calculated is not in the correct format ! " + distance);
        }
        //  xóa khi deloy
        if(mDistance > 10D)
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Success ", 30000));

        MoneyOfShip moneyOfShip = moneyOfShipRepository.getMoneyOfShipByDistance(mDistance);
        if(moneyOfShip == null){
            throw new ErrorNotFoundException("Can not calculate price by distance "+distance);
        }
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Success ", moneyOfShip.getPrice()));
    }


}
