package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.model.SliderItem;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.service.SliderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/slider-item")
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
    @PostMapping("")
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@RequestBody SliderItem sliderItem){
        return sliderItemService.addNewSliderItem(sliderItem);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getSliderItemsById(@PathVariable Long id){
        return sliderItemService.getSliderItemById(id);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateSliderItemsById(@PathVariable(value = "id") Long id, @RequestBody SliderItem newSliderItem)  {
        return sliderItemService.updateSliderItemById(id,newSliderItem);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteSliderItemsById(@PathVariable(value = "id") Long id){
        return sliderItemService.deleteSliderItemById(id);
    }
}
