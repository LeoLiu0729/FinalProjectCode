package com.csc340.jpacruddemo.TransferFunds;

import java.math.BigDecimal;
import java.util.List;
public interface TransferFundsService {
    List<TransferFunds> getAllTransfers();
    void transferAmount(String transferFrom, String transferTo, BigDecimal amount);
}
