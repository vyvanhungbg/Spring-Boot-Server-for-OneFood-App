package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorAccessDeniedException;
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

import javax.validation.Valid;


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
    ResponseEntity<ResponseObject> getAllCart() throws ErrorAccessDeniedException, ErrorNotFoundException {
            return cartService.getAllFoodInCart();
    }

    @GetMapping("/{id}")
    @PostAuthorize("@myService.castResponseObjectToCart(returnObject.body).idUser == authentication.principal.user.idUser or hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> getCartById(@PathVariable Long id) throws ErrorNotFoundException, ErrorAccessDeniedException {
        return cartService.getCartById(id);
    }


    @PostMapping("/update-amount")
    ResponseEntity<ResponseObject> changeAmountFoodInCart(@RequestParam(value = "idFood", defaultValue = "-1") long idFood, @RequestParam(value = "amount", defaultValue = "-1") int amount) throws ErrorAccessDeniedException, ErrorNotFoundException, ErrorExecutionFailedException {
        return cartService.changeAmountFoodInCart(idFood,amount);
    }

    @PostMapping("/update-status")
    ResponseEntity<ResponseObject> changeStatusFoodInCart(@RequestParam(value = "idFood", defaultValue = "-1") long idFood, @RequestParam(value = "status", defaultValue = "false") boolean status) throws ErrorAccessDeniedException, ErrorNotFoundException, ErrorExecutionFailedException {
        return cartService.changeStatusFoodInCart(idFood,status);
    }


    @PostMapping("")
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@Valid @RequestBody Cart user) throws ErrorExecutionFailedException {
        return cartService.addNewCart(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateCartById(@PathVariable(value = "id") Long id, @Valid @RequestBody Cart newCart) throws ErrorNotFoundException, ErrorAccessDeniedException {
        return cartService.updateCartById(id,newCart);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> deleteCartById(@PathVariable(value = "id") Long id) throws ErrorNotFoundException, ErrorExecutionFailedException {
        return cartService.deleteCartById(id);
    }
}
