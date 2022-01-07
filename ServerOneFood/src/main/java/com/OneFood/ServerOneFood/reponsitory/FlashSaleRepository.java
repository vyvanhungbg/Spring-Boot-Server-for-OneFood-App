package com.OneFood.ServerOneFood.reponsitory;

import com.OneFood.ServerOneFood.DTO.FoodDTO;
import com.OneFood.ServerOneFood.model.FlashSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FlashSaleRepository extends JpaRepository<FlashSale, Long> {
    @Query("SELECT s FROM FlashSale s  WHERE s.idFlashSale!=1")
    List<FlashSale> getAllFlashSale();
}
