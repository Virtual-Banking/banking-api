package com.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.dto.SignupRequest;
import com.bankingapp.dto.SignupResponse;
import com.bankingapp.model.Customer;
import com.bankingapp.service.AuthService;
import com.bankingapp.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private static AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> registerUser(@RequestBody @Valid SignupRequest request) {
        return ResponseEntity.ok(authService.registerUser(request));
    }
    
}
