package com.OneFood.ServerOneFood.reponsitory;

import com.OneFood.ServerOneFood.DTO.FoodDTO;
import com.OneFood.ServerOneFood.DTO.ItemInCartDTO;
import com.OneFood.ServerOneFood.model.Cart;
import com.OneFood.ServerOneFood.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findAllByIdUser(Long idUser);
    @Query("SELECT new com.OneFood.ServerOneFood.DTO.ItemInCartDTO(" +
            "    f,\n" +
            "   s,c.cartNumberOfFood, c.status) FROM Store s INNER JOIN s.foods f INNER JOIN  f.carts c WHERE c.idUser=:idUser")
    List<ItemInCartDTO> findAllFoodInCartByIdUser(@Param("idUser")Long idUser);

    Cart findByIdUserAndIdFood(Long idUser , Long idFood);
}
