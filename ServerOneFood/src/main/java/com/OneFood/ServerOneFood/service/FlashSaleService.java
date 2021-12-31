package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.DTO.FlashSaleDTO;
import com.OneFood.ServerOneFood.DTO.FoodDTO;
import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.model.FlashSale;
import com.OneFood.ServerOneFood.reponsitory.FlashSaleRepository;
import com.OneFood.ServerOneFood.reponsitory.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlashSaleService {
    @Autowired
    private final FlashSaleRepository flashSaleRepository;
    @Autowired
    private final FoodRepository foodRepository;

    public FlashSaleService(FlashSaleRepository flashSaleRepository, FoodRepository foodRepository) {
        this.flashSaleRepository = flashSaleRepository;
        this.foodRepository = foodRepository;
    }

    public ResponseEntity<ResponseObject> getAllFlashSale(){
        List<FlashSale> flashSales =  flashSaleRepository.findAll();
        if(flashSales.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty flash Sale list ", flashSales));
        List<FlashSaleDTO> list =  flashSales.stream().map(flashSale -> {
            List<FoodDTO> foodDTOS = flashSale.getFoods().stream().map(food -> {
                FoodDTO foodDTO =  foodRepository.getFoodDTOByID(food.getIdFood()).orElse(new FoodDTO());
                return foodDTO;
            }).collect(Collectors.toList());
            return new FlashSaleDTO(flashSale,foodDTOS);
        }).collect(Collectors.toList());
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find "+list.size()+" flash Sale ", list));

    }

    public List<FlashSale> getAllFlashSaleNormal(){
        List<FlashSale> list = flashSaleRepository.findAll();
        return list;
    }

    public void updateIsFlashSale(long idFlashSale, boolean isFlashSale) throws ErrorNotFoundException {
        FlashSale flashSale = flashSaleRepository.findById(idFlashSale).orElseThrow( ()->new ErrorNotFoundException("Can not find item Flash Sale"));
        flashSale.setFlashSale(isFlashSale);
        flashSaleRepository.save(flashSale);
    }

    public ResponseEntity<ResponseObject> addNewFlashSale(FlashSale flashSale) throws ErrorExecutionFailedException {
        FlashSale savedFlashSale =flashSaleRepository.save(flashSale);
        if(savedFlashSale == null)
            throw new ErrorExecutionFailedException("New flash Sale save failed !");
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New flash Sale save successful ! ", savedFlashSale));

    }

    public ResponseEntity<ResponseObject> updateFlashSaleById(Long id, FlashSale newFlashSale) throws ErrorNotFoundException, ErrorExecutionFailedException {
        FlashSale flashSale = flashSaleRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find flash Sale with id "+id));

        flashSale.setFlashSaleLevel(newFlashSale.getFlashSaleLevel());

        FlashSale updatedFlashSale = flashSaleRepository.save(flashSale);
        if(updatedFlashSale == null)
            throw new ErrorExecutionFailedException("Update flash Sale failed with id "+id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Update successful flash Sale with id "+id,updatedFlashSale));

    }

    public ResponseEntity<ResponseObject> getFlashSaleById(Long id) throws ErrorNotFoundException {
        FlashSale flashSale = flashSaleRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find flash Sale with id "+id));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Successful search flash Sale with id "+id,flashSale));
    }

    public ResponseEntity<ResponseObject> deleteFlashSaleById(Long id) throws ErrorNotFoundException, ErrorExecutionFailedException {
        FlashSale flashSale = flashSaleRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find flash Sale with id "+id)); flashSaleRepository.delete(flashSale);
        if(flashSaleRepository.existsById(id))
            throw new ErrorExecutionFailedException("Delete failed");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful flash Sale with id "+id,flashSale));
    }
}
