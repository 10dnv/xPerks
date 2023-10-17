package com.xperks.adapter;

import com.xperks.dto.transaction.TransactionModel;
import com.xperks.persistence.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionAdapter {

    public TransactionModel toTransactionModel(Transaction transaction) {
        if (transaction == null) {
            return null;
        }
        return  TransactionModel
                .builder()
                .id(transaction.getId())
                .sender(UserAdapter.toUserMainInfo(transaction.getSender()))
                .receiver(UserAdapter.toUserMainInfo(transaction.getReceiver()))
                .approver(UserAdapter.toUserMainInfo(transaction.getApprover()))
                .status(transaction.getStatus())
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .reason(transaction.getReason())
                .build();
    }
    
    public List<TransactionModel> toTransactionModelList(List<Transaction> transactions) {
        List<TransactionModel> transactionList = new ArrayList<>();
        transactions.forEach(transaction -> transactionList.add(toTransactionModel(transaction)));
        return transactionList;
    }
}
