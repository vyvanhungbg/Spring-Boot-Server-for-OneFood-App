package com.OneFood.ServerOneFood.Service;

import com.OneFood.ServerOneFood.Model.SliderItem;
import com.OneFood.ServerOneFood.Model.ResponseObject;
import com.OneFood.ServerOneFood.Reponsitory.SliderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SliderItemService {
    @Autowired
    private final SliderItemRepository sliderItemRepository;

    public SliderItemService(SliderItemRepository sliderItemRepository) {
        this.sliderItemRepository = sliderItemRepository;
    }


    public ResponseEntity<ResponseObject> getAllSliderItem(){
        List<SliderItem> sliderItem =  sliderItemRepository.findAll();
        if(sliderItem.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty sliderItem list ", sliderItem));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find all successful sliderItem ", sliderItem));

    }

    public ResponseEntity<ResponseObject> addNewSliderItem(SliderItem newSliderItem){
        SliderItem sliderItem = sliderItemRepository.save(newSliderItem);
        if(sliderItem == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"New sliderItem create failed ",sliderItem));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New sliderItem successfully created ",sliderItem));

    }

    public ResponseEntity<ResponseObject> updateSliderItemById(Long id, SliderItem newSliderItem)  {
        SliderItem sliderItem = sliderItemRepository.findById(id).orElse(null);
        if(sliderItem==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find sliderItem with id "+id,null));
        sliderItem.setImageUrl(newSliderItem.getImageUrl());
        sliderItem.setDescription(newSliderItem.getDescription());

        SliderItem updatedSliderItem = sliderItemRepository.save(sliderItem);
        if(updatedSliderItem == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"SliderItem update failed ",updatedSliderItem));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"SliderItem successfully updated ",updatedSliderItem));

    }

    public ResponseEntity<ResponseObject> getSliderItemById(Long id) {
        SliderItem sliderItem = sliderItemRepository.findById(id).orElse(null);
        if(sliderItem==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find sliderItem with id "+id,null));

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find successful sliderItem with id "+id,sliderItem));
    }

    public ResponseEntity<ResponseObject> deleteSliderItemById(Long id) {
        SliderItem sliderItem = sliderItemRepository.findById(id).orElse(null);
        if(sliderItem==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find sliderItem with id "+id,null));
        sliderItemRepository.delete(sliderItem);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful sliderItem with id "+id,sliderItem));
    }
}
