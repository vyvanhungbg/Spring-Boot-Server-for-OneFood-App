package com.OneFood.ServerOneFood.Reponsitory;

import com.OneFood.ServerOneFood.Model.FoodReviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodReviewsRepository extends JpaRepository<FoodReviews, Long> {
}
