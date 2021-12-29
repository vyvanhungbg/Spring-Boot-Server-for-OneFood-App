package com.OneFood.ServerOneFood.reponsitory;

import com.OneFood.ServerOneFood.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Long> {
    List<Bill> findAllByIdUser(long idUser);
}
