package com.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bankingapp.model.Customer;
import com.bankingapp.service.CustomerService;

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllUsers() {
        return ResponseEntity.ok(customerService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getUserById(id));
    }
}
