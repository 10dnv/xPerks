package com.xperks.service;

import com.xperks.dto.TransactionModel;
import com.xperks.dto.TransactionRequest;

public interface TransactionServiceIF {
    TransactionModel createTransaction(int senderId, TransactionRequest transactionRequest);
}
