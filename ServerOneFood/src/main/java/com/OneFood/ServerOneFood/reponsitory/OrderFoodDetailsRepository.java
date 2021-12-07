package com.OneFood.ServerOneFood.reponsitory;

import com.OneFood.ServerOneFood.model.OrderFoodDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderFoodDetailsRepository extends JpaRepository<OrderFoodDetails,Long> {
    List<OrderFoodDetails> findByIdBill(Long idBill);
}
