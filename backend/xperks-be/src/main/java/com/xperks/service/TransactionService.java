package com.xperks.service;

import com.xperks.adapter.TransactionAdapter;
import com.xperks.dto.*;
import com.xperks.persistence.Transaction;
import com.xperks.persistence.User;
import com.xperks.repository.TransactionRepository;
import com.xperks.security.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionService extends EntityManagerSupport implements TransactionServiceIF {

    private final UserServiceIF userService;
    private final TransactionAdapter transactionAdapter;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public TransactionModel createTransaction(TransactionRequest transactionRequest) {
        int currentUserId = AuthUtil.getAuthenticatedUserId();
        validateTransactionDetails(currentUserId, transactionRequest);
        User sender = userService.getUserById(currentUserId);
        User receiver = userService.getUserById(transactionRequest.getReceiverId());
        User approver = sender.getSuperior();
        Objects.requireNonNull(approver, "Superior is missing");
        if (receiver.getId() == sender.getSuperior().getId()) {
            /* if receiver is sender's superior, then approver should be receiver's superior */
            approver = receiver.getSuperior();
        }
        Transaction transaction = Transaction
                .builder()
                .sender(sender)
                .receiver(receiver)
                .approver(approver)
                .status(Status.PENDING)
                .amount(transactionRequest.getAmount())
                .description(transactionRequest.getDescription())
                .type(transactionRequest.getType())
                .build();
        entityManager.persist(transaction);
        return transactionAdapter.toTransactionModel(transaction);
    }

    @Override
    @Transactional
    public List<TransactionModel> getTransactionHistory() {
        /* get all transactions for logged user  */
        User user = userService.getUserById(AuthUtil.getAuthenticatedUserId());
        List<Transaction> transactions = transactionRepository.getTransactionHistoryListForUser(user);
        return transactionAdapter.toTransactionModelList(transactions);
    }

    @Override
    @Transactional
    public List<TransactionModel> getRequestsForApproval() {
        /* only superiors can approve transactions */
        if (!userService.isSuperior()) {
            return Collections.emptyList();
        }
        User user = userService.getUserById(AuthUtil.getAuthenticatedUserId());
        /* get all in pending transactions where logged user is approver */
        List<Transaction> transactions = transactionRepository.getTransactionsToBeApproved(user);
        return transactionAdapter.toTransactionModelList(transactions);
    }

    @Override
    @Transactional
    public void handleTransaction(int id, TransactionResponseType responseType) {
        int loggedUserId = AuthUtil.getAuthenticatedUserId();
        Transaction transaction = entityManager.find(Transaction.class, id);
        if (transaction.getApprover().getId() != loggedUserId) {
            throw new IllegalArgumentException("Current user cannot validate transaction with id " + transaction.getId());
        }
        if (TransactionResponseType.APPROVE.equals(responseType)) {
            transaction.setStatus(Status.APPROVED);
            User user = transaction.getReceiver();
            user.setBalance(user.getBalance() + transaction.getAmount().getPoints());
            entityManager.persist(user);
        } else {
            transaction.setStatus(Status.DENIED);
        }
        entityManager.persist(transaction);
    }

    private void validateTransactionDetails(int senderId, TransactionRequest transactionRequest) {
        if (transactionRequest == null) {
            throw new IllegalArgumentException("Data about transaction is missing");
        }
        if (senderId == transactionRequest.getReceiverId()) {
            throw new IllegalArgumentException("Sender cannot be the same as receiver");
        }
    }
}
