package com.bankingapp.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bankingapp.dto.TransferRequest;
import com.bankingapp.model.Account;
import com.bankingapp.model.Transaction;
import com.bankingapp.repository.AccountRepository;
import com.bankingapp.repository.TransactionRepository;
import com.bankingapp.service.AccountService;
import com.bankingapp.service.NotificationService;
import com.bankingapp.service.TransactionService;

import jakarta.transaction.Transactional;

public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private NotificationService notificationService;

    @Override
    public List<Transaction> getAllTransactions() {
        
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByAccount(Long accountId) {
        
        return transactionRepository.findByFromAccountOrToAccount(accountId, accountId);
    }

    @Transactional
    public void transferFunds(TransferRequest request) {
        Account from = accountService.findByAccountNumber(request.getFromAccount());
        Account to = accountService.findByAccountNumber(request.getToAccount());

        if (from.getBalance() < request.getAmount()) {
            throw new RuntimeException("Insufficient funds");
        }

        from.setBalance(from.getBalance() - request.getAmount());
        to.setBalance(to.getBalance() + request.getAmount());

        accountService.save(from);
        accountService.save(to);

        Transaction txn = new Transaction();
        txn.setFromAccount(from.getAccountNumber());
        txn.setToAccount(to.getAccountNumber());
        txn.setAmount(request.getAmount());
        txn.setTimestamp(LocalDateTime.now());
        transactionRepository.save(txn);

        notificationService.sendTransferAlert(from.getCustomer().getUsername(), request.getAmount());
    }
    
}
