package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.model.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyService {

    public List<Bill> cast(ResponseObject responseObject) {
        List<Bill> bills = (List<Bill>) responseObject.getData();
        return new ArrayList<>(bills);
    }

    public Bill castResponseObjectToBill(ResponseObject responseObject) {
        Bill bill = (Bill) responseObject.getData();
        return bill;
    }
    public Cart castResponseObjectToCart(ResponseObject responseObject) {
        Cart cart = (Cart) responseObject.getData();
        return cart;
    }

    public Long  getPrincipal(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return  ((CustomUserDetails)principal).getUser().getIdUser();
        } else {
            return null;
        }
    }

    public boolean  isRoleAdmin(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            Set<Role> roles =  ((CustomUserDetails)principal).getUser().getRoles();
            Optional<Role> mRole = roles.stream().filter(role -> role.getRoleName().toUpperCase().equals("ADMIN")).findAny();
            if(mRole.isEmpty())
                return false;
            else
                return true;
        } else {
            return false;
        }
    }
}