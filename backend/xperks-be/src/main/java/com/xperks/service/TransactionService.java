package com.xperks.service;

import com.xperks.adapter.TransactionAdapter;
import com.xperks.dto.Status;
import com.xperks.dto.TransactionModel;
import com.xperks.dto.TransactionRequest;
import com.xperks.persistence.Transaction;
import com.xperks.persistence.User;
import com.xperks.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public TransactionModel createTransaction(int senderId, TransactionRequest transactionRequest) {
        validateTransactionDetails(transactionRequest);
        User sender = userService.getUserById(senderId);
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
    public List<TransactionModel> getTransactionHistory(int id) {
        User user = userService.getUserById(id);
        List<Transaction> transactions = transactionRepository.getTransactionHistoryListForUser(user);
        return transactionAdapter.toTransactionModelList(transactions);
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
