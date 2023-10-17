package com.xperks.adapter;

import com.xperks.dto.enums.TransactionType;
import com.xperks.dto.notification.NotificationModel;
import com.xperks.dto.transaction.TransactionModel;
import com.xperks.persistence.Notification;
import com.xperks.security.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationAdapter {

    private final TransactionAdapter transactionAdapter;

    public NotificationModel toNotificationModel(Notification notification) {
        if (notification == null) {
            return null;
        }
        TransactionModel transaction = transactionAdapter.toTransactionModel(notification.getTransaction());
        return NotificationModel
                .builder()
                .id(notification.getId())
                .transactionModel(transaction)
                .unread(notification.isUnread())
                .type(AuthUtil.getAuthenticatedUserId() == transaction.getSender().getId() ? TransactionType.SENT : TransactionType.RECEIVED)
                .build();
    }

    public List<NotificationModel> toNotificationModelList(List<Notification> notifications) {
        List<NotificationModel> notificationModelList = new ArrayList<>();
        notifications.forEach(notification -> notificationModelList.add(toNotificationModel(notification)));
        return notificationModelList;
    }
}
