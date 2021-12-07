package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.model.TypeOfDiscountCode;
import com.OneFood.ServerOneFood.reponsitory.TypeOfDiscountCodeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeOfDiscountCodeService {
    private final TypeOfDiscountCodeRepository typeOfDiscountCodeRepository;

    public TypeOfDiscountCodeService(TypeOfDiscountCodeRepository typeOfDiscountCodeRepository) {
        this.typeOfDiscountCodeRepository = typeOfDiscountCodeRepository;
    }


    public ResponseEntity<ResponseObject> getAllTypeOfDiscountCode(){
        List<TypeOfDiscountCode> typeOfDiscountCodes =  typeOfDiscountCodeRepository.findAll();
        if(typeOfDiscountCodes.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty type of discount list ", typeOfDiscountCodes));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find all successful type of discount ", typeOfDiscountCodes));

    }

    public ResponseEntity<ResponseObject> addNewTypeOfDiscount(TypeOfDiscountCode typeOfDiscountCode) throws ErrorExecutionFailedException {
        TypeOfDiscountCode savedTypeOfDiscountCode =typeOfDiscountCodeRepository.save(typeOfDiscountCode);
        if(savedTypeOfDiscountCode == null)
            throw new ErrorExecutionFailedException("New type of discount save failed !");
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New type of discount save successful ! ", savedTypeOfDiscountCode));
    }

    public ResponseEntity<ResponseObject> updateTypeOfDiscountCodeById(Long id, TypeOfDiscountCode newTypeOfDiscountCode) throws ErrorNotFoundException, ErrorExecutionFailedException {
        TypeOfDiscountCode typeOfDiscountCode = typeOfDiscountCodeRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find type of discount with id "+id));

        typeOfDiscountCode.setTypeOfDiscountCodeName(newTypeOfDiscountCode.getTypeOfDiscountCodeName());
        typeOfDiscountCode.setFoodDiscountCodes(newTypeOfDiscountCode.getFoodDiscountCodes());

        TypeOfDiscountCode updatedTypeOfDiscountCode = typeOfDiscountCodeRepository.save(typeOfDiscountCode);
        if(updatedTypeOfDiscountCode!=null)
            throw new ErrorExecutionFailedException("Update successful type of food with id "+id);
        return  ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"New type of discount update failed !", null));
    }

    public ResponseEntity<ResponseObject> getTypeOfDiscountCodeById(Long id) throws ErrorNotFoundException {
        TypeOfDiscountCode typeOfDiscountCode = typeOfDiscountCodeRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find type of discount with id "+id));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Successful search type of discount with id "+id,typeOfDiscountCode));

    }

    public ResponseEntity<ResponseObject> deleteTypeOfDiscountCodeById(Long id) throws ErrorNotFoundException {
        TypeOfDiscountCode typeOfDiscountCode = typeOfDiscountCodeRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find type of discount with id "+id));
        typeOfDiscountCodeRepository.delete(typeOfDiscountCode);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful type of discount with id "+id,typeOfDiscountCode));

    }
}
