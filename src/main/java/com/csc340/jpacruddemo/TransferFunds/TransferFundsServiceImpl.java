package com.csc340.jpacruddemo.TransferFunds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csc340.jpacruddemo.user.User;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferFundsServiceImpl implements TransferFundsService {
    private final TransferFundsRepository transferFundsRepository;

    @Autowired
    public TransferFundsServiceImpl(TransferFundsRepository transferFundsRepository) {
        this.transferFundsRepository = transferFundsRepository;
    }

    @Override
    public List<TransferFunds> getAllTransfers() {
        return transferFundsRepository.findAll();
    }

    @Override
    public List<TransferFunds> getTransfersForUser(User user) {
        return transferFundsRepository.findByUser(user);
    }

    @Override
    public void transferAmount(User user, String transferFrom, String transferTo, BigDecimal amount) {
        TransferFunds transfer = new TransferFunds();
        transfer.setUser(user);
        transfer.setTransferFrom(transferFrom);
        transfer.setTransferTo(transferTo);
        transfer.setAmount(amount);

        transferFundsRepository.save(transfer);
    }
}