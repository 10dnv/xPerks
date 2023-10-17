package com.xperks.repository.transaction;

import com.xperks.persistence.Transaction;
import com.xperks.persistence.User;

import java.util.List;

public interface CustomTransactionRepository {
    List<Transaction> getTransactionHistoryListForUser(User user);

    List<Transaction> getTransactionsToBeApproved(User user);

    int countInPendingTransactions(int id);
}
