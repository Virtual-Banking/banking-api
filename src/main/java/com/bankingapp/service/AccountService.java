package com.bankingapp.service;

import java.util.List;

import com.bankingapp.model.Account;

public interface AccountService {
    List<Account> getAllAccounts();
    Account getAccountById(Long id);
    Account findByAccountNumber(String accountNumber);
    void saveAccount(Account account);
}
