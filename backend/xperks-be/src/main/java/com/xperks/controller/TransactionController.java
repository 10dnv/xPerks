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

    @PostMapping("/{senderId}")
    @ResponseBody
    public TransactionModel createTransaction(@PathVariable int senderId,
                                              @RequestBody TransactionRequest transactionRequest) {
        return transactionService.createTransaction(senderId, transactionRequest);
    }

    @GetMapping("/{id}/history")
    @ResponseBody
    public List<TransactionModel> getTransactionHistory(@PathVariable int id) {
        return transactionService.getTransactionHistory(id);
    }

    @GetMapping("/{id}/approval-request")
    @ResponseBody
    public List<TransactionModel> getRequestsForApproval(@PathVariable int id) {
        return transactionService.getRequestsForApproval(id);
    }
}
