package com.rsaad.transaction.model;

import com.rsaad.transaction.constants.TransactionConstants;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity Transaction class that reflect database fields
 * this class is mapped to database"
 * @author Raed
 */
@Entity
@Table(name = "t_transactions")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    @NotBlank(message = TransactionConstants.CURRENCY_REQUIRED_MESSAGE)
    @Column(name = "trx_currency", nullable = false)
    private String transactionCurrency;

    @NotNull(message = TransactionConstants.AMOUNT_REQUIRED_MESSAGE)
    @Min(value=0, message = TransactionConstants.TRANSACTION_MIN_VALUE_MESSAGE)
    @Column(name = "trx_amount", nullable = false)
    private Double transactionAmount;

    @NotBlank(message = TransactionConstants.ACCOUNT_REQUIRED_MESSAGE)
    @Column(name = "trx_account_id", nullable = false)
    @Size(min=7,max=11,message=TransactionConstants.ACCOUNT_NUMBER_LENGTH_VALIDATION_MESSAGE)
    private String transactionAccountId;
}
