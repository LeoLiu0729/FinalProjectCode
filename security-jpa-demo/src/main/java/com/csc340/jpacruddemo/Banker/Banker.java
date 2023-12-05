package com.csc340.jpacruddemo.Banker;

import com.csc340.jpacruddemo.user.User;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Banker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountID;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String zip;
    private String state;
    private String dob;
    private String ssn;
    private String temporaryPassword;
    private double balance;
    @OneToOne
    @JoinColumn(name ="user_id")
    private User user;
}