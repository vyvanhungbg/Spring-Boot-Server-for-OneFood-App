package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.DTO.FoodDTO;
import com.OneFood.ServerOneFood.exception.ErrorAccessDeniedException;
import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.Food;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.model.Cart;
import com.OneFood.ServerOneFood.reponsitory.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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


    public ResponseEntity<ResponseObject> getAllCart() throws ErrorAccessDeniedException {

        Long idUser = myService.getPrincipal();
        if(idUser == null) throw new ErrorAccessDeniedException("Access is denied");
        List<Cart> carts =  cartRepository.findAllByIdUser(idUser);
        if(carts.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty cart list of user id  "+idUser, carts));

        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find "+carts.size()+" cart successful", carts));
    }

    public ResponseEntity<ResponseObject> getAllFoodInCart() throws ErrorAccessDeniedException {

        Long idUser = myService.getPrincipal();
        if(idUser == null) throw new ErrorAccessDeniedException("Access is denied");
        List<FoodDTO> foods =  cartRepository.findAllFoodInCartByIdUser(idUser);
        if(foods.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty cart list of user id  "+idUser, foods));

        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find "+foods.size()+" foods in cart ", foods));
    }


    public ResponseEntity<ResponseObject> addNewCart(Cart newCart) throws ErrorExecutionFailedException {
        if(cartRepository.existsById(newCart.getIdCart()))
            throw new ErrorExecutionFailedException("New cart create failed Because this item already exists");
        Long idUser = myService.getPrincipal();
        newCart.setIdUser(idUser);
        Cart cart = cartRepository.save(newCart);
        if(cart == null)
            throw new ErrorExecutionFailedException("New product create failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New product successfully created ",cart));

    }

    public ResponseEntity<ResponseObject> updateCartById(Long id, Cart newCart) throws ErrorNotFoundException, ErrorAccessDeniedException {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find product with id "+id));
        Long idUser = myService.getPrincipal();
        if(cart.getIdUser() != idUser && !myService.isRoleAdmin()){
            throw new ErrorAccessDeniedException("Access is denied");
        }
        cart.setCartNumberOfFood(newCart.getCartNumberOfFood());
        cart.setStatus(newCart.isStatus());
        Cart updatedCart = cartRepository.save(cart);
        if(updatedCart == null)
            throw  new ErrorNotFoundException("Product update failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Product successfully updated ",updatedCart));

    }

    public ResponseEntity<ResponseObject> changeAmountFoodInCart(Long idFood, int amount) throws ErrorAccessDeniedException, ErrorExecutionFailedException, ErrorNotFoundException {
        if(idFood<=0 || amount <0){
            throw new ErrorExecutionFailedException("Validation erorr, idFood > 0 và amount >=0");
        }

        Long idUser = myService.getPrincipal();
        if(idUser == null) throw new ErrorAccessDeniedException("Access is denied");
        Cart cart = cartRepository.findByIdUserAndIdFood(idUser,idFood);
        if(amount ==0 ){  // nếu vật phẩm có sl = 0 thì xóa khỏi giỏ hàng
            return deleteCartById(cart.getIdCart());
        }
        ResponseEntity response;
        if(cart  != null){  // tìm thấy thì update amount
            cart.setCartNumberOfFood(amount);
            cartRepository.save(cart);
            response = ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Update amount of food successful ",cart));
        }else {  // nếu chưa có thì tạo mới trong giỏ hàng
            Cart newCart = new Cart();
            newCart.setIdFood(idFood);
            newCart.setCartNumberOfFood(amount);
            newCart.setStatus(false);
            newCart.setIdUser(idUser);
            response = addNewCart(newCart);
        }
        return response;
    }

    public ResponseEntity<ResponseObject> getCartById(Long id) throws ErrorNotFoundException, ErrorAccessDeniedException {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find product with id "+id));
        Long idUser = myService.getPrincipal();
        if(idUser!=cart.getIdUser())
            throw new ErrorAccessDeniedException("Access is denied");
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
