package com.bankingapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService{

    // @Autowired
    // private JavaMailSender mailSender;

    public void sendTransferAlert(String recipient, Double amount) {
        // SimpleMailMessage message = new SimpleMailMessage();
        // message.setTo(recipient);
        // message.setSubject("Transfer Alert");
        // message.setText("An amount of " + amount + " has been debited from your account.");
        // mailSender.send(message);

        System.out.println("Transfer Sent!");
    }
    
}
