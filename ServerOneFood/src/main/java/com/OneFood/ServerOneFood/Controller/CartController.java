package com.OneFood.ServerOneFood.Controller;

import com.OneFood.ServerOneFood.Model.ResponseObject;
import com.OneFood.ServerOneFood.Model.Cart;
import com.OneFood.ServerOneFood.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/cart")
@RestController
public class CartController {
    @Autowired
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping("")
    ResponseEntity<ResponseObject> getAllTypeOfDiscountCode(){
        return cartService.getAllCart();
    }
    @PostMapping("")
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@RequestBody Cart user){
        return cartService.addNewCart(user);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getCartById(@PathVariable Long id){
        return cartService.getCartById(id);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateCartById(@PathVariable(value = "id") Long id, @RequestBody Cart newCart)  {
        return cartService.updateCartById(id,newCart);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteCartById(@PathVariable(value = "id") Long id){
        return cartService.deleteCartById(id);
    }
}
