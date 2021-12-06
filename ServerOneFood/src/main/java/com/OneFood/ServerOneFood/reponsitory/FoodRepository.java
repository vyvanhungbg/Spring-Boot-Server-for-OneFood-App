package com.OneFood.ServerOneFood.reponsitory;

import com.OneFood.ServerOneFood.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Long> {
}
