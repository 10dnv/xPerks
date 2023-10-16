package com.xperks.repository;

import com.xperks.persistence.Transaction;
import com.xperks.persistence.User;
import com.xperks.service.EntityManagerSupport;

import java.util.List;

public class CustomTransactionRepositoryImpl extends EntityManagerSupport implements CustomTransactionRepository {

    @Override
    public List<Transaction> getTransactionHistoryListForUser(User user) {
        return entityManager
                .createQuery("FROM Transaction WHERE sender = :user OR receiver = :user", Transaction.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<Transaction> getTransactionsToBeApproved(User user) {
        return entityManager
                .createQuery("FROM Transaction WHERE approver = :user AND status = 'PENDING'", Transaction.class)
                .setParameter("user", user)
                .getResultList();
    }
}
