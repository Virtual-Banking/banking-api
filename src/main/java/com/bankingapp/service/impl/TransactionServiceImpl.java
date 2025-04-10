package com.bankingapp.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bankingapp.dto.TransactionEvent;
import com.bankingapp.dto.TransferRequest;
import com.bankingapp.model.Account;
import com.bankingapp.model.Transaction;
import com.bankingapp.repository.TransactionRepository;
import com.bankingapp.service.AccountService;
import com.bankingapp.service.NotificationService;
import com.bankingapp.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TRANSACTION_TOPIC = "transaction-events";

    @Override
    public List<Transaction> getAllTransactions() {
        
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByAccount(Long accountId) {
        
        return transactionRepository.findByFromAccountOrToAccount(accountId, accountId);
    }

    @Transactional
    public void transferFunds(TransferRequest request) {
        Account from = accountService.findByAccountNumber(request.getFromAccount());
        Account to = accountService.findByAccountNumber(request.getToAccount());

        if (from.getBalance() < request.getAmount()) {
            throw new RuntimeException("Insufficient funds");
        }

        from.setBalance(from.getBalance() - request.getAmount());
        to.setBalance(to.getBalance() + request.getAmount());

        accountService.save(from);
        accountService.save(to);

        Transaction txn = new Transaction();
        txn.setFromAccount(from.getAccountNumber());
        txn.setToAccount(to.getAccountNumber());
        txn.setAmount(request.getAmount());
        txn.setTimestamp(LocalDateTime.now());

        //Send event message to kafka
        sendTransactionEventMessage(txn);
        
        //save the transaction in the repository
        transactionRepository.save(txn);

        notificationService.sendTransferAlert(from.getCustomer().getUsername(), request.getAmount());
    }
    
    private void sendTransactionEventMessage(Transaction transaction){
        TransactionEvent event = new TransactionEvent();
        event.setFromAccount(transaction.getFromAccount().toString());
        event.setToAccount(transaction.getToAccount().toString());
        event.setAmount(transaction.getAmount());
        event.setTimestamp(transaction.getTimestamp());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonEvent = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TRANSACTION_TOPIC, jsonEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize transaction event", e);
        }
    }
}
