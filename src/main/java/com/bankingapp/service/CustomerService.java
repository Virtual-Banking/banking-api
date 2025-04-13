package com.bankingapp.service;

import java.util.List;

import com.bankingapp.model.Customer;

public interface CustomerService {

    Customer getCustomerById(Long id);

    List<Customer> getAllCustomers();

    boolean existsByUsername(String username);

    Customer saveCustomer(Customer user);
    
}
