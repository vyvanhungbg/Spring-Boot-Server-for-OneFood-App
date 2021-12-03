package com.OneFood.ServerOneFood.Service;

import com.OneFood.ServerOneFood.Model.ResponseObject;
import com.OneFood.ServerOneFood.Model.Cart;
import com.OneFood.ServerOneFood.Reponsitory.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    public ResponseEntity<ResponseObject> getAllCart(){
        List<Cart> carts =  cartRepository.findAll();
        if(carts.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty cart list ", carts));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find all successful cart ", carts));

    }

    public ResponseEntity<ResponseObject> addNewCart(Cart newCart){
        Cart cart = cartRepository.save(newCart);
        if(cart == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"New product create failed ",cart));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New product successfully created ",cart));

    }

    public ResponseEntity<ResponseObject> updateCartById(Long id, Cart newCart)  {
        Cart cart = cartRepository.findById(id).orElse(null);
        if(cart==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find product with id "+id,null));
        cart.setCartNumberOfFood(newCart.getCartNumberOfFood());
        cart.setStatus(newCart.isStatus());

        Cart updatedCart = cartRepository.save(cart);
        if(updatedCart == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"Product update failed ",updatedCart));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Product successfully updated ",updatedCart));

    }

    public ResponseEntity<ResponseObject> getCartById(Long id) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if(cart==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find product with id "+id,null));

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find successful product with id "+id,cart));
    }

    public ResponseEntity<ResponseObject> deleteCartById(Long id) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if(cart==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find product with id "+id,null));
        cartRepository.delete(cart);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful product with id "+id,cart));
    }
}
