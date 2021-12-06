package com.OneFood.ServerOneFood.reponsitory;

import com.OneFood.ServerOneFood.model.FoodOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOptionRepository extends JpaRepository<FoodOption,Long> {
}
