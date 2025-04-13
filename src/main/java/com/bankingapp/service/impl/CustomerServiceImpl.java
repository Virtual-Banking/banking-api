package com.bankingapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.model.Customer;
import com.bankingapp.repository.CustomerRepository;
import com.bankingapp.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getCustomerById(Long id) {
        
        return customerRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public boolean existsByUsername(String username) {
        return customerRepository.findByUsername(username).isPresent();
    }

    @Override
    public Customer saveCustomer(Customer user) {
        return customerRepository.save(user);
    }

    
    
}
