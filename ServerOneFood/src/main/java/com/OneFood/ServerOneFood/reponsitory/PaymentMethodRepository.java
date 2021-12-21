package com.OneFood.ServerOneFood.reponsitory;

import com.OneFood.ServerOneFood.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository< PaymentMethod,Long> {
}
