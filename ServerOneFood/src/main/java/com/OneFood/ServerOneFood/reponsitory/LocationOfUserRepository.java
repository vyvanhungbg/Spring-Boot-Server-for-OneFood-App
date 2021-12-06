package com.OneFood.ServerOneFood.reponsitory;

import com.OneFood.ServerOneFood.model.LocationOfUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationOfUserRepository extends JpaRepository<LocationOfUser,Long> {

    List<LocationOfUser> findByIsDefaultLocation(boolean isDefaultLocation);
}
