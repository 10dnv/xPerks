package com.xperks.service.transaction;

import com.xperks.dto.enums.TransactionResponseType;
import com.xperks.dto.transaction.TransactionModel;
import com.xperks.dto.transaction.TransactionRequest;

import java.util.List;

public interface TransactionServiceIF {
    TransactionModel createTransaction(TransactionRequest transactionRequest);

    List<TransactionModel> getTransactionHistory();

    List<TransactionModel> getRequestsForApproval();

    void handleTransaction(int id, TransactionResponseType responseType);

    int getNumberOfInPendingTransactions();
}
