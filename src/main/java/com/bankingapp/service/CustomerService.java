package com.bankingapp.service;

import java.util.List;

import com.bankingapp.model.Customer;

public interface CustomerService {

    Customer getUserById(Long id);

    List<Customer> getAllUsers();
    
}
