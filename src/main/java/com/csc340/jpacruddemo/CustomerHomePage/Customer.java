package com.csc340.jpacruddemo.CustomerHomePage;

import com.csc340.jpacruddemo.user.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")  // This is the foreign key column in the Customer table
    private User user;
    private String accountNumber;
    private Double accountBalance;

//    public Customer(Long id, String accountNumber, Double accountBalance) {
//        this.id = id;
//        this.accountBalance = accountBalance;
//        this.accountNumber = accountNumber;
//    }
}


