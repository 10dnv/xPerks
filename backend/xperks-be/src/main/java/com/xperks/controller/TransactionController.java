package com.xperks.controller;

import com.xperks.dto.transaction.TransactionModel;
import com.xperks.dto.transaction.TransactionRequest;
import com.xperks.dto.enums.TransactionResponseType;
import com.xperks.service.transaction.TransactionServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionServiceIF transactionService;

    @PostMapping
    @ResponseBody
    public TransactionModel createTransaction(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.createTransaction(transactionRequest);
    }

    @GetMapping("/history")
    @ResponseBody
    public List<TransactionModel> getTransactionHistory() {
        return transactionService.getTransactionHistory();
    }

    @GetMapping("/approval-request")
    @ResponseBody
    public List<TransactionModel> getRequestsForApproval() {
        return transactionService.getRequestsForApproval();
    }


    @PutMapping("/{id}/handle-request")
    public void handleTransaction(@PathVariable int id, @RequestParam TransactionResponseType responseType) {
        transactionService.handleTransaction(id, responseType);
    }

    @GetMapping("/pending")
    @ResponseBody
    public int getNumberOfInPendingTransactions() {
        return transactionService.getNumberOfInPendingTransactions();
    }
}
