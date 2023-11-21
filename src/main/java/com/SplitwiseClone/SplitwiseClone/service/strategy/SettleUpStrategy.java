package com.SplitwiseClone.SplitwiseClone.service.strategy;

import com.SplitwiseClone.SplitwiseClone.DTO.TransactionDTO;
import com.SplitwiseClone.SplitwiseClone.model.Expense;

import java.util.List;

public interface SettleUpStrategy {
    List<TransactionDTO> settleUp(List<Expense> expenses);
}
