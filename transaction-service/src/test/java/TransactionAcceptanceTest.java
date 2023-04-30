package com.rsaad.transaction;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsaad.transaction.constants.TransactionConstants;
import com.rsaad.transaction.dto.TransactionRequest;
import com.rsaad.transaction.dto.TransactionResponse;
import com.rsaad.transaction.exceptions.validation.ErrorResult;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Acceptance Testing Class")
public class TransactionAcceptanceTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private static final String API_URI = "/api/transactions";

    @BeforeEach
    void setUp() {
        restTemplate
                .getRestTemplate()
                .setMessageConverters(Collections.singletonList(new MappingJackson2HttpMessageConverter()));
    }

    @Test
    @DisplayName("Create a correct Transaction Test Case")
    public void testCreateTransaction() {
        TransactionRequest transactionRequest=
                TransactionRequest
                .builder()
                .currency("USD")
                .accountNumber("6724301068")
                .amount(10.6).build();

        ResponseEntity<TransactionResponse> responseEntity =
                restTemplate.postForEntity(API_URI, transactionRequest,
                        TransactionResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody().getMessage()).isEqualTo(TransactionConstants.SUCCESS);
    }

    @Test
    @DisplayName("Create a Transaction with Negative amount should return validation exception message")
    public void testCreateTransactionValidationWithNegativeAmount() {
        TransactionRequest transactionRequest=
                TransactionRequest
                        .builder()
                        .currency("USD")
                        .accountNumber("6724301068")
                        .amount(-1.0).build();

        ResponseEntity<ErrorResult> responseEntity =
                restTemplate.postForEntity(API_URI, transactionRequest,
                        ErrorResult.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getFieldErrors().get(0).getMessage()).isEqualTo(TransactionConstants.TRANSACTION_MIN_VALUE_MESSAGE);
    }

    @Test
    @DisplayName("Create a Transaction with wrong accounnt number should return validation exception message")
    public void testCreateTransactionValidationWithWrongAccountNumber() {
        TransactionRequest transactionRequest=
                TransactionRequest
                        .builder()
                        .currency("USD")
                        .accountNumber("6724")
                        .amount(1.0).build();

        ResponseEntity<ErrorResult> responseEntity =
                restTemplate.postForEntity(API_URI, transactionRequest,
                        ErrorResult.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getFieldErrors().get(0).getMessage()).isEqualTo(TransactionConstants.ACCOUNT_NUMBER_LENGTH_VALIDATION_MESSAGE);
    }

    @Test
    @DisplayName("Create a Transaction with no amount should return validation exception message")
    public void testCreateTransactionValidationWithNoAmount() {
        TransactionRequest transactionRequest=
                TransactionRequest
                        .builder()
                        .currency("USD")
                        .accountNumber("6724301068")
                        .build();

        ResponseEntity<ErrorResult> responseEntity =
                restTemplate.postForEntity(API_URI, transactionRequest,
                        ErrorResult.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getFieldErrors().get(0).getMessage()).isEqualTo(TransactionConstants.AMOUNT_REQUIRED_MESSAGE);
    }

    @Test
    @DisplayName("Create a Transaction with empty request should return validation exception message")
    public void testCreateTransactionValidationWithEmptyRequest() {
        TransactionRequest transactionRequest=
                TransactionRequest
                        .builder().build();

        ResponseEntity<ErrorResult> responseEntity =
                restTemplate.postForEntity(API_URI, transactionRequest,
                        ErrorResult.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("Create a Transaction with no currency should return validation exception message")
    public void testCreateTransactionValidationWithNoCurrency() {
        TransactionRequest transactionRequest=
                TransactionRequest
                        .builder()
                        .accountNumber("6724301068")
                        .amount(2.0).build();

        ResponseEntity<ErrorResult> responseEntity =
                restTemplate.postForEntity(API_URI, transactionRequest,
                        ErrorResult.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getFieldErrors().get(0).getMessage()).isEqualTo(TransactionConstants.CURRENCY_REQUIRED_MESSAGE);
    }
}
