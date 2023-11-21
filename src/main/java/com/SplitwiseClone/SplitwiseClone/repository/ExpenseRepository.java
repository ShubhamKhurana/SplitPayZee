package com.SplitwiseClone.SplitwiseClone.repository;

import com.SplitwiseClone.SplitwiseClone.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
}
