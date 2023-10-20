package com.xperks.service.notification;

import com.xperks.adapter.NotificationAdapter;
import com.xperks.dto.notification.NotificationModel;
import com.xperks.persistence.Notification;
import com.xperks.persistence.Transaction;
import com.xperks.repository.notification.NotificationRepository;
import com.xperks.security.AuthUtil;
import com.xperks.service.EntityManagerSupport;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NotificationService extends EntityManagerSupport implements NotificationServiceIF {

    private final NotificationRepository notificationRepository;
    private final NotificationAdapter notificationAdapter;

    @Override
    @Transactional
    public void createNotification(Transaction transaction) {
        Objects.requireNonNull(transaction, "Transaction is missing.");
        Notification notification = Notification
                .builder()
                .unread(true)
                .transaction(transaction)
                .build();
        entityManager.persist(notification);
    }

    @Override
    @Transactional
    public List<NotificationModel> getNotifications() {
        return notificationAdapter.toNotificationModelList(notificationRepository.getNotificationsForUser(AuthUtil.getAuthenticatedUserId()));
    }

    @Override
    @Transactional
    public void readNotification(int notificationId) {
        Notification notification = notificationRepository.findById(notificationId);
        Objects.requireNonNull(notification);
        notification.setUnread(false);
        entityManager.persist(notification);
    }

}
