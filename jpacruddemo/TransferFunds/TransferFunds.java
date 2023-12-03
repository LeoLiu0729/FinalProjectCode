package com.csc340.jpacruddemo.TransferFunds;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
//@Table(name = "transactions")
@NoArgsConstructor
@Getter
@Setter

public class TransferFunds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transferFrom;
    private String transferTo;
    private BigDecimal amount;
    private Date transactionDate;

    public TransferFunds(Long id, String transferFrom, String transferTo, BigDecimal amount) {
        this.id = id;
        this.transferFrom = transferFrom;
        this.transferTo = transferTo;
        this.amount = amount;
    }
}
