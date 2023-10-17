package com.xperks.service.notification;

import com.xperks.dto.notification.NotificationModel;
import com.xperks.persistence.Transaction;

import java.util.List;

public interface NotificationServiceIF {
    void createNotification(Transaction transaction);

    List<NotificationModel> getNotifications();
}
