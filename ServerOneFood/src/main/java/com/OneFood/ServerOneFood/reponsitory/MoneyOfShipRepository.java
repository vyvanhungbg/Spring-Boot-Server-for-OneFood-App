package com.OneFood.ServerOneFood.reponsitory;

import com.OneFood.ServerOneFood.model.MoneyOfShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MoneyOfShipRepository extends JpaRepository<MoneyOfShip, Long> {
    @Query("SELECT s FROM MoneyOfShip s WHERE :distance >= s.underTheDistance AND :distance <s.overTheDistance ")
    MoneyOfShip getMoneyOfShipByDistance(@Param("distance")Double distance);
}
