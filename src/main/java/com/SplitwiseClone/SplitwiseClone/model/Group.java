package com.SplitwiseClone.SplitwiseClone.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "SPLITWISE_GROUP")
public class Group extends BaseModel{
    private String name;
    private String description;
    private double totalAmountSpent;

    @Enumerated(EnumType.STRING)
    private Currency defaultCurrency;

    @OneToMany
    private List<Expense> expenses;

    @ManyToMany(mappedBy = "groups")
    private List<User> users;
}
