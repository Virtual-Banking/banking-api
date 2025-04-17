package com.bankingapp.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bankingapp.dto.SignupRequest;
import com.bankingapp.dto.SignupResponse;
import com.bankingapp.model.Account;
import com.bankingapp.model.Currency;
import com.bankingapp.model.Customer;
import com.bankingapp.model.UserType;
import com.bankingapp.service.AccountService;
import com.bankingapp.service.AuthService;
import com.bankingapp.service.CustomerService;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private CustomerService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SignupResponse registerUser(SignupRequest request) {

        if (userService.existsByUsername(request.getUsername())) {
            return new SignupResponse();
        }

        Customer user = new Customer();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserType.USER);
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());
        user = userService.saveCustomer(user);

        Account account = new Account();
        account.setAccountNumber("ACC" + UUID.randomUUID().toString().substring(0, 8));
        account.setBalance(request.getInitialBalance());
        account.setCustomer(user);
        
        //Set Currency
        if(request.getCountry().equalsIgnoreCase("United States"))
        account.setCurrency(Currency.USD);
        else if(request.getCountry().equalsIgnoreCase("India"))
        account.setCurrency(Currency.INR);
        else if(request.getCountry().equalsIgnoreCase("Japan"))
        account.setCurrency(Currency.JPY);
        else{
            System.out.println("Invalid currency");
            return null;
        }

        accountService.saveAccount(account);

        return 
            new SignupResponse(
                "User registered successfully",
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                user.getAddress(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getCurrency().toString()
            );
    }
    
}

