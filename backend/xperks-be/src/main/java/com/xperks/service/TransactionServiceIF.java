package com.xperks.service;

import com.xperks.dto.TransactionModel;
import com.xperks.dto.TransactionRequest;

import java.util.List;

public interface TransactionServiceIF {
    TransactionModel createTransaction(TransactionRequest transactionRequest);

    List<TransactionModel> getTransactionHistory();

    List<TransactionModel> getRequestsForApproval();
}
