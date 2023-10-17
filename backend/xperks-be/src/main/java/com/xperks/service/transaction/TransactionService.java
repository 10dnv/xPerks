package com.xperks.service.transaction;

import com.xperks.adapter.TransactionAdapter;
import com.xperks.dto.enums.Status;
import com.xperks.dto.enums.TransactionResponseType;
import com.xperks.dto.transaction.TransactionModel;
import com.xperks.dto.transaction.TransactionRequest;
import com.xperks.persistence.CompanyWallet;
import com.xperks.persistence.Transaction;
import com.xperks.persistence.User;
import com.xperks.repository.companyWallet.CompanyWalletRepository;
import com.xperks.repository.transaction.TransactionRepository;
import com.xperks.security.AuthUtil;
import com.xperks.service.EntityManagerSupport;
import com.xperks.service.notification.NotificationServiceIF;
import com.xperks.service.user.UserServiceIF;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionService extends EntityManagerSupport implements TransactionServiceIF {

    private final UserServiceIF userService;
    private final TransactionAdapter transactionAdapter;
    private final TransactionRepository transactionRepository;
    private final CompanyWalletRepository companyWalletRepository;
    private final NotificationServiceIF notificationService;

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
                .reason(transactionRequest.getType())
                .transactionDate(new Date())
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
    public void handleTransaction(int transactionId, TransactionResponseType responseType) {
        int loggedUserId = AuthUtil.getAuthenticatedUserId();
        Transaction transaction = entityManager.find(Transaction.class, transactionId);
        if (transaction.getApprover().getId() != loggedUserId) {
            throw new IllegalArgumentException("Current user cannot validate transaction with id " + transaction.getId());
        }
        if (TransactionResponseType.APPROVE.equals(responseType)) {
            transaction.setStatus(Status.APPROVED);
            User user = transaction.getReceiver();
            int points = transaction.getAmount().getPoints();
            CompanyWallet wallet = getCompanyWallet();
            wallet.setAmount(wallet.getAmount() - points);
            validateWalletAmount();
            user.setBalance(user.getBalance() + points);
            entityManager.persist(user);
            entityManager.persist(wallet);
        } else {
            transaction.setStatus(Status.DENIED);
        }
        entityManager.persist(transaction);
        notificationService.createNotification(transaction);
    }

    @Override
    @Transactional
    public int getNumberOfInPendingTransactions() {
        if (!userService.isSuperior()) {
            return  0;
        }
        int currentUserId = AuthUtil.getAuthenticatedUserId();
        return transactionRepository.countInPendingTransactions(currentUserId);
    }

    private void validateTransactionDetails(int senderId, TransactionRequest transactionRequest) {
        if (transactionRequest == null) {
            throw new IllegalArgumentException("Data about transaction is missing");
        }
        if (senderId == transactionRequest.getReceiverId()) {
            throw new IllegalArgumentException("Sender cannot be the same as receiver");
        }
    }

    private CompanyWallet getCompanyWallet() {
        CompanyWallet wallet = (CompanyWallet) companyWalletRepository.findAll().stream().findFirst().orElse(null);
        Objects.requireNonNull(wallet, "Company wallet is missing");
        return wallet;
    }

    private void validateWalletAmount() {
        CompanyWallet wallet = getCompanyWallet();
        if (wallet.getAmount() < 0) {
            throw new IllegalArgumentException("Insufficient funds. Transaction cannot be approved");
        }
    }
}
