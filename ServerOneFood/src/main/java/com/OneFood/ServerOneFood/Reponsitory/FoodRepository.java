package com.OneFood.ServerOneFood.Reponsitory;

import com.OneFood.ServerOneFood.Model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Long> {
}
