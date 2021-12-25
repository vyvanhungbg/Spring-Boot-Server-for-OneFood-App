package com.OneFood.ServerOneFood.reponsitory;

import com.OneFood.ServerOneFood.DTO.FoodDTO;
import com.OneFood.ServerOneFood.model.Cart;
import com.OneFood.ServerOneFood.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findAllByIdUser(Long idUser);
    @Query("SELECT new com.OneFood.ServerOneFood.DTO.FoodDTO(f.idFood,\n" +
            "    f.idStore,\n" +
            "    f.idTypeOfFood,\n" +
            "    f.foodName,\n" +
            "    f.foodImage,\n" +
            "    f.foodPrice,\n" +
            "    f.foodDescribe,\n" +
            "    f.foodTag,\n" +
            "    f.foodStar) FROM Food f INNER JOIN  f.carts c WHERE c.idUser=:idUser")
    List<FoodDTO> findAllFoodInCartByIdUser(@Param("idUser")Long idUser);

    Cart findByIdUserAndIdFood(Long idUser , Long idFood);
}
