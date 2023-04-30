package com.rsaad.transaction.controller;

import com.rsaad.transaction.dto.TransactionRequest;
import com.rsaad.transaction.dto.TransactionResponse;
import com.rsaad.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Transaction Controller class
 * will act as an entry point for requests
 * @author Raed
 */
@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TransactionResponse> placeOrder(@RequestBody TransactionRequest transactionRequest) {
        TransactionResponse transactionResponse = transactionService.processTransaction(transactionRequest);
        return new ResponseEntity<>(transactionResponse,HttpStatus.CREATED);
    }
}