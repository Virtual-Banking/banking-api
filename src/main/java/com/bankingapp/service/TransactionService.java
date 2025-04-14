package com.bankingapp.service;

import java.util.List;

import com.bankingapp.dto.TransferRequest;
import com.bankingapp.model.Transaction;

public interface TransactionService {
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByAccount(String accountFrom);
    void transferFunds(TransferRequest request);
}

