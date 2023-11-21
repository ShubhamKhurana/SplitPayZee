package com.SplitwiseClone.SplitwiseClone.repository;

import com.SplitwiseClone.SplitwiseClone.model.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExpenseRepository extends JpaRepository<UserExpense, Integer> {
}
