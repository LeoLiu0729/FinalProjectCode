package com.csc340.jpacruddemo.CustomerHomePage;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private Double accountBalance;

    public Customer(String accountNumber, Double accountBalance) {
        this.accountBalance = accountBalance;
        this.accountNumber = accountNumber;
    }
}


