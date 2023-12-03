package com.csc340.jpacruddemo.CustomerLoanApplication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CustomerLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String full_name;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zip;
    private Integer Loan;

    public CustomerLoan(String full_name, String email, String address, String city, String state, String zip, Integer Loan) {
        this.full_name = full_name;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.Loan = Loan;
    }
}
