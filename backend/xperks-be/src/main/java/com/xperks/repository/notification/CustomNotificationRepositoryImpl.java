package com.xperks.repository.notification;

import com.xperks.persistence.Notification;
import com.xperks.service.EntityManagerSupport;

import java.util.List;

public class CustomNotificationRepositoryImpl extends EntityManagerSupport implements CustomNotificationRepository {

    @Override
    public List<Notification> getNotificationsForUser(int userId) {
        return entityManager.createQuery("FROM Notification WHERE transaction.sender.id = :id OR transaction.receiver.id = :id", Notification.class)
                .setParameter("id", userId)
                .getResultList();
    }
}
