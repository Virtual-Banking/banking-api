package com.bankingapp.service;

import com.bankingapp.dto.SignupRequest;
import com.bankingapp.dto.SignupResponse;

public interface AuthService {

    SignupResponse registerUser(SignupRequest request);
    
} 
