package com.OneFood.ServerOneFood.Service;

import com.OneFood.ServerOneFood.Model.ResponseObject;
import com.OneFood.ServerOneFood.Model.TypeOfDiscountCode;
import com.OneFood.ServerOneFood.Model.TypeOfFood;
import com.OneFood.ServerOneFood.Reponsitory.TypeOfDiscountCodeRepository;
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

    public ResponseEntity<ResponseObject> addNewTypeOfDiscount(TypeOfDiscountCode typeOfDiscountCode){
        TypeOfDiscountCode savedTypeOfDiscountCode =typeOfDiscountCodeRepository.save(typeOfDiscountCode);
        if(savedTypeOfDiscountCode == null)
            return  ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"New type of discount save failed !", null));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New type of discount save successful ! ", savedTypeOfDiscountCode));
    }

    public ResponseEntity<ResponseObject> updateTypeOfDiscountCodeById(Long id, TypeOfDiscountCode newTypeOfDiscountCode)  {
        TypeOfDiscountCode typeOfDiscountCode = typeOfDiscountCodeRepository.findById(id).orElse(null);
        if(typeOfDiscountCode==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find type of discount with id "+id,null));
        typeOfDiscountCode.setTypeOfDiscountCodeName(newTypeOfDiscountCode.getTypeOfDiscountCodeName());
        typeOfDiscountCode.setFoodDiscountCodes(newTypeOfDiscountCode.getFoodDiscountCodes());

        TypeOfDiscountCode updatedTypeOfDiscountCode = typeOfDiscountCodeRepository.save(typeOfDiscountCode);
        if(updatedTypeOfDiscountCode!=null)
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Update successful type of food with id "+id,typeOfDiscountCode));
        return  ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"New type of discount update failed !", null));
    }

    public ResponseEntity<ResponseObject> getTypeOfDiscountCodeById(Long id) {
        Optional<TypeOfDiscountCode> typeOfDiscountCode = typeOfDiscountCodeRepository.findById(id);
        if(typeOfDiscountCode.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Successful search type of discount with id "+id,typeOfDiscountCode));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find type discount with id "+id,null));
    }

    public ResponseEntity<ResponseObject> deleteTypeOfDiscountCodeById(Long id) {
        TypeOfDiscountCode typeOfDiscountCode = typeOfDiscountCodeRepository.findById(id).orElse(null);
        if(typeOfDiscountCode==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find type of discount with id "+id,null));
        typeOfDiscountCodeRepository.delete(typeOfDiscountCode);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful type of discount with id "+id,typeOfDiscountCode));

    }
}
