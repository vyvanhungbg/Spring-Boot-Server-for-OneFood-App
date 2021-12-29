package com.OneFood.ServerOneFood.reponsitory;

import com.OneFood.ServerOneFood.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
    User findByUserEmail(String userEmail);

}
