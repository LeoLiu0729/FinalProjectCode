package com.csc340.jpacruddemo.WithdrawalDeposit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "account_number")
    private String accountNumber;

    //@Column(name = "transaction_type")
    private String transactionType;

    //@Column(name = "amount")
    private BigDecimal amount;

    //@Column(name = "transaction_date")
    private Date transactionDate;

    public Transaction(Long id, String accountNumber, String transactionType, BigDecimal amount) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
    }
}

