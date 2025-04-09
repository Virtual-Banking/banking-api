package com.bankingapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bankingapp.model.Account;
import com.bankingapp.repository.AccountRepository;
import com.bankingapp.service.AccountService;

public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long id) {
        
        return accountRepository.findById(id).orElseThrow();
    }

    @Override
    public Account findByAccountNumber(Long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }
    
}
