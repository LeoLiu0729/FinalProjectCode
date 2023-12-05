package com.csc340.jpacruddemo.TransferFunds;

import com.csc340.jpacruddemo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TransferFundsRepository extends JpaRepository<TransferFunds, Long>{
    List<TransferFunds> findByUser(User user);
}