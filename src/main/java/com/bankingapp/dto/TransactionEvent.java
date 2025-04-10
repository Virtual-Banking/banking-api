package com.bankingapp.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TransactionEvent {
    private String fromAccount;
    private String toAccount;
    private Double amount;
    private LocalDateTime timestamp;

}
