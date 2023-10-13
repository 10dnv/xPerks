package com.xperks.controller;

import com.xperks.dto.TransactionModel;
import com.xperks.dto.TransactionRequest;
import com.xperks.service.TransactionServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
