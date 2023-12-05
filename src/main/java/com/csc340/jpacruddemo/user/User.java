package com.csc340.jpacruddemo.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String role; // 可以是 "USER", "BANKER", "MANAGER" 等
    private String userName;
    private BigDecimal balance; // 使用 BigDecimal 而非 double 来精确表示金额
    private String accountNumber;
    private LocalDate dob; // 使用 LocalDate 来表示日期
    private String ssn;
    private String address;
    private String zip;
    private String state;

}
