package com.bankingapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bankingapp.model.Transaction;
import com.bankingapp.repository.TransactionRepository;
import com.bankingapp.service.TransactionService;

public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransactions() {
        
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByAccount(Long accountId) {
        
        return transactionRepository.findByFromAccountOrToAccount(accountId, accountId);
    }
    
}
