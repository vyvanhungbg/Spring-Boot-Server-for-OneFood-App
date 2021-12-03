package com.OneFood.ServerOneFood.Reponsitory;

import com.OneFood.ServerOneFood.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
