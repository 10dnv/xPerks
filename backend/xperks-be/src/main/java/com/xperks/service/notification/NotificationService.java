package com.xperks.service.notification;

import com.xperks.persistence.Notification;
import com.xperks.persistence.Transaction;
import com.xperks.service.EntityManagerSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NotificationService extends EntityManagerSupport implements NotificationServiceIF {

    @Override
    public void createNotification(Transaction transaction) {
        Objects.requireNonNull(transaction, "Transaction is missing.");
        Notification notification = Notification
                .builder()
                .unread(true)
                .transaction(transaction)
                .build();
        entityManager.persist(notification);
    }

}
