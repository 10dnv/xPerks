package com.xperks.dto.notification;

import com.xperks.dto.enums.TransactionType;
import com.xperks.dto.transaction.TransactionModel;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationModel {

    private int id;

    @NotNull
    private TransactionModel transactionModel;
    private boolean unread;
    private TransactionType type;
}