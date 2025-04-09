package com.bankingapp.service;

public interface NotificationService {

    void sendTransferAlert(String username, Double amount);

    
}