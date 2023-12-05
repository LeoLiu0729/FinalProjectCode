package com.csc340.jpacruddemo.TransferFunds;

import com.csc340.jpacruddemo.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transfer_funds", uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
@NoArgsConstructor
@Getter
@Setter

public class TransferFunds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")  // This is the foreign key column in the Customer table
    private User user;
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
