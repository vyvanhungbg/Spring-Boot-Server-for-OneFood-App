package com.OneFood.ServerOneFood.Reponsitory;

import com.OneFood.ServerOneFood.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
}
