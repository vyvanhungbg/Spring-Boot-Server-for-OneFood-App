package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.SliderItem;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.service.SliderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("one-app/v1/slider-item")
public class SliderItemController {
    @Autowired
    private final SliderItemService sliderItemService;

    public SliderItemController(SliderItemService sliderItemService) {
        this.sliderItemService = sliderItemService;
    }


    @GetMapping("")
    ResponseEntity<ResponseObject> getAllSliderItem(){
        return sliderItemService.getAllSliderItem();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getSliderItemsById(@PathVariable Long id) throws ErrorNotFoundException {
        return sliderItemService.getSliderItemById(id);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@Valid @RequestBody SliderItem sliderItem) throws ErrorExecutionFailedException {
        return sliderItemService.addNewSliderItem(sliderItem);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> updateSliderItemsById(@PathVariable(value = "id") Long id,@Valid @RequestBody SliderItem newSliderItem) throws ErrorExecutionFailedException, ErrorNotFoundException {
        return sliderItemService.updateSliderItemById(id,newSliderItem);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> deleteSliderItemsById(@PathVariable(value = "id") Long id) throws ErrorNotFoundException, ErrorExecutionFailedException {
        return sliderItemService.deleteSliderItemById(id);
    }
}
