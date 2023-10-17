package com.xperks.service.notification;

import com.xperks.persistence.Transaction;

public interface NotificationServiceIF {
    void createNotification(Transaction transaction);
}
