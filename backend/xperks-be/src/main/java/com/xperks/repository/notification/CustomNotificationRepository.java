package com.xperks.repository.notification;

import com.xperks.persistence.Notification;

import java.util.List;

public interface CustomNotificationRepository {

    List<Notification> getNotificationsForUser(int userId);
}
