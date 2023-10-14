package com.xperks.adapter;

import com.xperks.dto.TransactionModel;
import com.xperks.persistence.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionAdapter {

    public TransactionModel toTransactionModel(Transaction transaction) {
        return  TransactionModel
                .builder()
                .id(transaction.getId())
                .sender(UserAdapter.getUserMainInfo(transaction.getSender()))
                .receiver(UserAdapter.getUserMainInfo(transaction.getReceiver()))
                .approver(UserAdapter.getUserMainInfo(transaction.getApprover()))
                .status(transaction.getStatus())
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .build();
    }
}
