package com.OneFood.ServerOneFood.reponsitory;

import com.OneFood.ServerOneFood.model.FlashSale;
import com.OneFood.ServerOneFood.model.FoodDiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodDiscountCodeRepository extends JpaRepository<FoodDiscountCode,Long> {
    @Query("SELECT s FROM FoodDiscountCode s  WHERE s.forAllUser =1 ")
    List<FoodDiscountCode> getAllFoodDiscountCode();
}
