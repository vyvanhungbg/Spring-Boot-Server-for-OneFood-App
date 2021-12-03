package com.OneFood.ServerOneFood.Reponsitory;

import com.OneFood.ServerOneFood.Model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
