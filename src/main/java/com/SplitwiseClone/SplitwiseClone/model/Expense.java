package com.SplitwiseClone.SplitwiseClone.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name="SPLITWISE_EXPENSE")
public class Expense extends BaseModel{
    private double amount;
    private String description;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToMany
    private List<UserExpense> userExpenses;
}
