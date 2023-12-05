package com.csc340.jpacruddemo.user;

import com.csc340.jpacruddemo.CreditCardApplication.CreditCard;
import com.csc340.jpacruddemo.LoanApplication.Loan;
import com.csc340.jpacruddemo.TransferFunds.TransferFunds;
import com.csc340.jpacruddemo.WithdrawalDeposit.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author csc340-f23
 */
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;
    private String email;
    private String role;
    private String password;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CreditCard creditCard;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Loan loan;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransferFunds> transferFundsList = new ArrayList<>();


    public User(String userName, String email, String role, String password) {
        this.userName = userName;
        this.email = email;
        this.role = role;
        this.password = password;
    }

}
