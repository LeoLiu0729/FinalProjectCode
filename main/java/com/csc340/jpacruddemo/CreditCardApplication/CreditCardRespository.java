package com.csc340.jpacruddemo.CreditCardApplication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRespository extends JpaRepository<CreditCard, Long> {
}
