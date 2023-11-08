package com.SplitwiseClone.SplitwiseClone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="SPLITWISE_USEREXPENSE")
public class UserExpense extends BaseModel{
    @ManyToOne
    private User user;
    private double amount;

    @Enumerated(EnumType.STRING)
    private UserExpenseType userExpenseType;
}

/*
     User    UserExpenseType
       1          m
       1          1

*/