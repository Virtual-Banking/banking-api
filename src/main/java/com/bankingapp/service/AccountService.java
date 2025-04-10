package com.bankingapp.service;

import java.util.List;

import com.bankingapp.model.Account;

public interface AccountService {
    List<Account> getAllAccounts();
    Account getAccountById(Long id);
    Account findByAccountNumber(Long accountNumber);
    void save(Account account);
}
