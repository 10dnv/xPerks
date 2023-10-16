package com.xperks.controller;

import com.xperks.dto.TransactionModel;
import com.xperks.dto.TransactionRequest;
import com.xperks.service.TransactionServiceIF;
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
}
