package com.OneFood.ServerOneFood.Reponsitory;

import com.OneFood.ServerOneFood.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Long> {
}
