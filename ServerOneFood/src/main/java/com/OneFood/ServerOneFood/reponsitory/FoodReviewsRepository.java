package com.OneFood.ServerOneFood.reponsitory;

import com.OneFood.ServerOneFood.model.FoodReviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodReviewsRepository extends JpaRepository<FoodReviews, Long> {
}
