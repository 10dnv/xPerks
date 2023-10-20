package com.xperks.repository.notification;

import com.xperks.persistence.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>, CustomNotificationRepository {

    Notification findById(int id);
}
