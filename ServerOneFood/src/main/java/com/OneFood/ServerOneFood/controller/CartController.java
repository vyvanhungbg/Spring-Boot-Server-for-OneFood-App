package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.model.Cart;
import com.OneFood.ServerOneFood.service.CartService;
import com.OneFood.ServerOneFood.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("one-app/v1/cart")
@PreAuthorize("isAuthenticated()")
public class CartController {
    @Autowired
    private final CartService cartService;
    @Autowired
    private final MyService myService;
    public CartController(CartService cartService, MyService myService) {
        this.cartService = cartService;
        this.myService = myService;
    }

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllTypeOfDiscountCode(){
        return cartService.getAllCart();
    }

    @GetMapping("/{id}")
    @PostAuthorize("@myService.castResponseObjectToCart(returnObject.body).idUser == authentication.principal.user.idUser or hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> getCartById(@PathVariable Long id) throws ErrorNotFoundException {
        return cartService.getCartById(id);
    }

    @PostMapping("")
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@RequestBody Cart user) throws ErrorExecutionFailedException {
        return cartService.addNewCart(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateCartById(@PathVariable(value = "id") Long id, @RequestBody Cart newCart) throws ErrorNotFoundException {
        return cartService.updateCartById(id,newCart);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> deleteCartById(@PathVariable(value = "id") Long id) throws ErrorNotFoundException, ErrorExecutionFailedException {
        return cartService.deleteCartById(id);
    }
}
