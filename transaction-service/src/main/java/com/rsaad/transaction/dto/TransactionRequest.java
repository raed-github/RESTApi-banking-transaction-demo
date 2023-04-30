package com.rsaad.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO TransactionRequest Class  that consist of request object
 * which will be displayed for client
 * @author Raed
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private String currency;
    private Double amount;
    private String accountNumber;
}
