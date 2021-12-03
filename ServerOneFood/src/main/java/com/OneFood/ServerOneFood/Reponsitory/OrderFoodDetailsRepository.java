package com.OneFood.ServerOneFood.Reponsitory;

import com.OneFood.ServerOneFood.Model.OrderFoodDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodDetailsRepository extends JpaRepository<OrderFoodDetails,Long> {
}
