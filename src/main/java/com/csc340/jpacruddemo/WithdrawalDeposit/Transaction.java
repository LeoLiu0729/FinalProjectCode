package com.csc340.jpacruddemo.WithdrawalDeposit;

import com.csc340.jpacruddemo.user.User;
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

    @OneToOne
    @JoinColumn(name = "user_id")  // This is the foreign key column in the Customer table
    private User user;

    public Transaction(Long id, String accountNumber, String transactionType, BigDecimal amount) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
    }
}

