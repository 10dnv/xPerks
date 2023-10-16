package com.xperks.service;

import com.xperks.dto.*;

import java.util.List;

public interface TransactionServiceIF {
    TransactionModel createTransaction(TransactionRequest transactionRequest);

    List<TransactionModel> getTransactionHistory();

    List<TransactionModel> getRequestsForApproval();

    void handleTransaction(int id, TransactionResponseType responseType);
}
