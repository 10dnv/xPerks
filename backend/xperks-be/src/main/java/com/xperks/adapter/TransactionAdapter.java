package com.xperks.adapter;

import com.xperks.dto.TransactionModel;
import com.xperks.persistence.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
                .type(transaction.getType())
                .build();
    }
    
    public List<TransactionModel> toTransactionModelList(List<Transaction> transactions) {
        List<TransactionModel> transactionList = new ArrayList<>();
        transactions.forEach(transaction -> transactionList.add(toTransactionModel(transaction)));
        return transactionList;
    }
}
