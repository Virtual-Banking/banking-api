package com.bankingapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bankingapp.model.Account;

@Service
public interface AccountService {
    List<Account> getAllAccounts();
    Account getAccountById(Long id);
    Account findByAccountNumber(Long accountNumber);
    void save(Account account);
}
