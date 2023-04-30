package com.rsaad.transaction.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsaad.transaction.controller.TransactionController;
import com.rsaad.transaction.dto.TransactionRequest;
import com.rsaad.transaction.dto.TransactionResponse;
import com.rsaad.transaction.service.TransactionService;
import com.rsaad.transaction.service.impl.TransactionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@DisplayName("Transaction Controller Integration Testing")
public class TransactionControllerIntegrationTesting {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    @DisplayName("Transaction Controller place order method must return success")
    public void testShouldReturnSuccessAndStatusCreated() throws Exception {
        TransactionRequest transactionRequest =
                TransactionRequest
                        .builder()
                        .currency("USD")
                        .accountNumber("12345678")
                        .amount(10.6).build();

        TransactionResponse transactionResponse = TransactionResponse.builder().message("success").build();

        when(transactionService.processTransaction(transactionRequest)).thenReturn(transactionResponse);

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"message\": \"success\"}"))
                .andExpect(status().isCreated());
    }
}
