package com.OneFood.ServerOneFood.reponsitory;

import com.OneFood.ServerOneFood.model.OrderFoodDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodDetailsRepository extends JpaRepository<OrderFoodDetails,Long> {
}
