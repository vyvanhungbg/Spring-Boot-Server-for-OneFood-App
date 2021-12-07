package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.SliderItem;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.reponsitory.SliderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

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
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find "+sliderItem.size()+" sliderItem ", sliderItem));

    }

    public ResponseEntity<ResponseObject> addNewSliderItem(SliderItem newSliderItem) throws ErrorExecutionFailedException {
        if(sliderItemRepository.existsById(newSliderItem.getIdSliderItem()))
            throw new ErrorExecutionFailedException("New slider create failed .Because this item already exists");

        SliderItem sliderItem = sliderItemRepository.save(newSliderItem);
        if(sliderItem == null)
            throw new ErrorExecutionFailedException("New sliderItem create failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New sliderItem successfully created ",sliderItem));

    }

    public ResponseEntity<ResponseObject> updateSliderItemById(Long id, SliderItem newSliderItem) throws ErrorNotFoundException, ErrorExecutionFailedException {
        SliderItem sliderItem = sliderItemRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find sliderItem with id "+id));

        sliderItem.setImageUrl(newSliderItem.getImageUrl());
        sliderItem.setDescription(newSliderItem.getDescription());

        SliderItem updatedSliderItem = sliderItemRepository.save(sliderItem);
        if(updatedSliderItem == null)
            throw new ErrorExecutionFailedException("SliderItem update failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"SliderItem successfully updated ",updatedSliderItem));

    }

    public ResponseEntity<ResponseObject> getSliderItemById(Long id) throws ErrorNotFoundException {
        SliderItem sliderItem = sliderItemRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find sliderItem with id "+id));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find successful sliderItem with id "+id,sliderItem));
    }

    public ResponseEntity<ResponseObject> deleteSliderItemById(Long id) throws ErrorNotFoundException, ErrorExecutionFailedException {
        SliderItem sliderItem = sliderItemRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find sliderItem with id "+id));
        sliderItemRepository.delete(sliderItem);
        if(sliderItemRepository.existsById(id))
            throw new ErrorExecutionFailedException("Delete slide item failed with id "+id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful sliderItem with id "+id,sliderItem));
    }
}
