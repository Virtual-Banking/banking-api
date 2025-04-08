package com.bankingapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bankingapp.model.Customer;

@Service
public interface CustomerService {

    Customer getUserById(Long id);

    List<Customer> getAllUsers();
    
}
