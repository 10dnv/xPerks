package com.xperks.repository;

import com.xperks.persistence.Transaction;
import com.xperks.persistence.User;

import java.util.List;

public interface CustomTransactionRepository {
    List<Transaction> getTransactionHistoryListForUser(User user);
}
