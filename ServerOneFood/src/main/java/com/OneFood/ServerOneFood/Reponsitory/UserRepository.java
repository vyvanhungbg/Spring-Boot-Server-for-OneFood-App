package com.OneFood.ServerOneFood.Reponsitory;

import com.OneFood.ServerOneFood.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);

}
