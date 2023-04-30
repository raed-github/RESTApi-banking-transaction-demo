package com.rsaad.transaction.service;

import com.rsaad.transaction.dto.TransactionRequest;
import com.rsaad.transaction.dto.TransactionResponse;
import com.rsaad.transaction.model.Transaction;
import com.rsaad.transaction.repository.TransactionRepository;
import com.rsaad.transaction.service.impl.TransactionServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@DisplayName("Transaction Service Integration Testing")
public class TransactionServiceIntegrationTesting {
    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    @DisplayName("Transaction Service placeOrder should store data and return success")
    public void testTransactionService(){
        TransactionRequest transactionRequest=
                TransactionRequest
                        .builder()
                        .currency("USD")
                        .accountNumber("12345678")
                        .amount(10.6).build();

        Transaction transaction = Transaction
                .builder()
                .transactionId(1234L)
                .transactionCurrency("USD")
                .transactionAccountId("12345678")
                .transactionAmount(10.6).build();

        TransactionResponse transactionResponse = TransactionResponse.builder().message("success").build();

        TransactionResponse result = transactionService.processTransaction(transactionRequest);
        assertEquals("success", result.getMessage());
    }
}
