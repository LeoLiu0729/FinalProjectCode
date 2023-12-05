package com.csc340.jpacruddemo.CreditCardApplication;

import com.csc340.jpacruddemo.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String full_name;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zip;
    private Integer credit_limit;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public CreditCard(String full_name, String email, String address, String city, String state, String zip, Integer credit_limit) {
        this.full_name = full_name;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.credit_limit = credit_limit;
    }
}