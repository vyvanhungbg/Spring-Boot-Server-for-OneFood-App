package com.OneFood.ServerOneFood.Reponsitory;

import com.OneFood.ServerOneFood.Model.LocationOfUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationOfUserRepository extends JpaRepository<LocationOfUser,Long> {

    List<LocationOfUser> findByIsDefaultLocation(boolean isDefaultLocation);
}
