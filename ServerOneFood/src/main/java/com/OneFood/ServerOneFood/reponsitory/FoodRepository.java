package com.OneFood.ServerOneFood.reponsitory;

import com.OneFood.ServerOneFood.DTO.FoodDTO;
import com.OneFood.ServerOneFood.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food,Long> {
    @Query("SELECT new com.OneFood.ServerOneFood.DTO.FoodDTO(f,s) FROM Store s  INNER JOIN  s.foods f where f.idFlashSale =1")
    List<FoodDTO> getAllFoodDTO();

    @Query("SELECT new com.OneFood.ServerOneFood.DTO.FoodDTO(f,s) FROM Store s  INNER JOIN  s.foods f WHERE f.idFood=:idFood")
    Optional<FoodDTO> getFoodDTOByID(@Param("idFood")Long idFood);
}
