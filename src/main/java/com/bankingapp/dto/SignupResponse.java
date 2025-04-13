package com.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupResponse {
    private String message;
    private String username;
    private String name;
    private String email;
    private String address;
    private String accountNumber;
    private Double initialBalance;
}
