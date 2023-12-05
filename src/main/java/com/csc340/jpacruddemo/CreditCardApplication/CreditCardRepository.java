package com.csc340.jpacruddemo.CreditCardApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

//    @Modifying
//    @Query("update CreditCard c set c.credit_limit = ?1 where c.id = ?2")
//    void updateCreditLimit(Integer newLimit, Long id);
}

