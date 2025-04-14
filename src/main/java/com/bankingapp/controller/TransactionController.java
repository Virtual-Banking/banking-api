package com.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.dto.TransferRequest;
import com.bankingapp.model.Transaction;
import com.bankingapp.service.TransactionService;


@RestController
@RequestMapping("/transactions")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccount(@PathVariable String accountFrom) {
        return ResponseEntity.ok(transactionService.getTransactionsByAccount(accountFrom));
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@Validated @RequestBody TransferRequest request) {
        transactionService.transferFunds(request);
        return ResponseEntity.ok("Transfer successful.");
    }
}
