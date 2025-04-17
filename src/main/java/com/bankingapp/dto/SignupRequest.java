package com.bankingapp.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    private String role = "USER";

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    private String name;
    
    private String address;

    private Double initialBalance = 0.0;

    @NotBlank(message="Country is required")
    private String country;

}
