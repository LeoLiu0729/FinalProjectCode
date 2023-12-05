package com.csc340.jpacruddemo.TransferFunds;

import com.csc340.jpacruddemo.user.User;

import java.math.BigDecimal;
import java.util.List;
public interface TransferFundsService {
    List<TransferFunds> getAllTransfers();
//    void transferAmount(String transferFrom, String transferTo, BigDecimal amount);

    List<TransferFunds> getTransfersForUser(User user);
    void transferAmount(User user, String transferFrom, String transferTo, BigDecimal amount);
}