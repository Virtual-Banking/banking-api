package com.bankingapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bankingapp.model.Transaction;

@Service
public interface TransactionService {
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByAccount(Long accountId);
}

