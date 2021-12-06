package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.model.Cart;
import com.OneFood.ServerOneFood.reponsitory.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private final CartRepository cartRepository;
    @Autowired
    private final MyService myService;
    public CartService(CartRepository cartRepository, MyService myService) {
        this.cartRepository = cartRepository;
        this.myService = myService;
    }


    public ResponseEntity<ResponseObject> getAllCart(){
        List<Cart> carts =  cartRepository.findAll();
        List<Cart> newCarts = new ArrayList<>(carts);
        Long idUser = myService.getPrincipal();
        if(!myService.isRoleAdmin())
            newCarts = carts.stream().filter(bill -> bill.getIdUser() == idUser).collect(Collectors.toList());
        if(carts.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty cart list ", carts));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find "+newCarts.size()+" cart successful", newCarts));

    }

    public ResponseEntity<ResponseObject> addNewCart(Cart newCart) throws ErrorExecutionFailedException {
        Long idUser = myService.getPrincipal();
        newCart.setIdUser(idUser);
        Cart cart = cartRepository.save(newCart);
        if(cart == null)
            throw new ErrorExecutionFailedException("New product create failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New product successfully created ",cart));

    }

    public ResponseEntity<ResponseObject> updateCartById(Long id, Cart newCart) throws ErrorNotFoundException {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find product with id "+id));
        Long idUser = myService.getPrincipal();
        if(cart.getIdUser() != idUser && !myService.isRoleAdmin()){
            throw new AccessDeniedException("Access is denied");
        }
        cart.setCartNumberOfFood(newCart.getCartNumberOfFood());
        cart.setStatus(newCart.isStatus());
        Cart updatedCart = cartRepository.save(cart);
        if(updatedCart == null)
            throw  new ErrorNotFoundException("Product update failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Product successfully updated ",updatedCart));

    }

    public ResponseEntity<ResponseObject> getCartById(Long id) throws ErrorNotFoundException {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find product with id "+id));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find successful product with id "+id,cart));
    }

    public ResponseEntity<ResponseObject> deleteCartById(Long id) throws ErrorNotFoundException, ErrorExecutionFailedException {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find product with id "+id));
        cartRepository.delete(cart);
        if(cartRepository.existsById(id))
            throw new ErrorExecutionFailedException("Delete cart failed with id "+id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful product with id "+id,cart));
    }
}
