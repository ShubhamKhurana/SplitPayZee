package com.SplitwiseClone.SplitwiseClone.service;

import com.SplitwiseClone.SplitwiseClone.model.*;
import com.SplitwiseClone.SplitwiseClone.repository.ExpenseRepository;
import com.SplitwiseClone.SplitwiseClone.repository.GroupRepository;
import com.SplitwiseClone.SplitwiseClone.repository.UserExpenseRepository;
import com.SplitwiseClone.SplitwiseClone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitServiceImpl implements InitService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserExpenseRepository userExpenseRepository;

    public void initialise(){

        Group group1 = new Group();
        group1.setName("Go Goa Gone");
        group1.setDescription("Goa Trip!");
        group1.setDefaultCurrency(Currency.INR);

        // This group will have an Id
        Group savedGroup = groupRepository.save(group1);


        User userA = User.builder()
                .name("A").email("A@gmail.com").phoneNumber("123").groups(List.of(savedGroup)).build();

        User userB = User.builder()
                .name("B").email("B@gmail.com").phoneNumber("456").groups(List.of(savedGroup)).build();

        User userC = User.builder()
                .name("C").email("C@gmail.com").phoneNumber("321").groups(List.of(savedGroup)).build();

        User userD = User.builder()
                .name("D").email("D@gmail.com").phoneNumber("654").groups(List.of(savedGroup)).build();

        User userE = User.builder()
                .name("E").email("E@gmail.com").phoneNumber("789").groups(List.of(savedGroup)).build();

        User userF = User.builder()
                .name("F").email("F@gmail.com").phoneNumber("987").groups(List.of(savedGroup)).build();

        savedGroup.setUsers(List.of(userA, userB, userC, userD, userE, userF));
//        savedGroup = groupRepository.save(savedGroup);

        User savedUserA = userRepository.save(userA);
        User savedUserB = userRepository.save(userB);
        User savedUserC = userRepository.save(userC);
        User savedUserD = userRepository.save(userD);
        User savedUserE = userRepository.save(userE);
        User savedUserF = userRepository.save(userF);

        savedGroup.setUsers(List.of(savedUserA, savedUserB, savedUserC, savedUserD, savedUserE, savedUserF));
        savedGroup = groupRepository.save(savedGroup);

        UserExpense userExpenseA = new UserExpense();
        userExpenseA.setUserExpenseType(UserExpenseType.HAD_TO_PAY);
        userExpenseA.setAmount(500);
        userExpenseA.setUser(savedUserA);
        UserExpense savedUserExpenseA = userExpenseRepository.save(userExpenseA);

        UserExpense userExpenseB = new UserExpense();
        userExpenseB.setUserExpenseType(UserExpenseType.HAD_TO_PAY);
        userExpenseB.setAmount(2000);
        userExpenseB.setUser(savedUserB);
        UserExpense savedUserExpenseB = userExpenseRepository.save(userExpenseB);

        UserExpense userExpenseC = new UserExpense();
        userExpenseC.setUserExpenseType(UserExpenseType.HAD_TO_PAY);
        userExpenseC.setAmount(500);
        userExpenseC.setUser(savedUserC);
        UserExpense savedUserExpenseC = userExpenseRepository.save(userExpenseC);

        UserExpense userExpenseD = new UserExpense();
        userExpenseD.setUserExpenseType(UserExpenseType.PAID);
        userExpenseD.setAmount(1500);
        userExpenseD.setUser(savedUserD);
        UserExpense savedUserExpenseD = userExpenseRepository.save(userExpenseD);

        UserExpense userExpenseE = new UserExpense();
        userExpenseE.setUserExpenseType(UserExpenseType.PAID);
        userExpenseE.setAmount(500);
        userExpenseE.setUser(savedUserE);
        UserExpense savedUserExpenseE = userExpenseRepository.save(userExpenseE);

        UserExpense userExpenseF = new UserExpense();
        userExpenseF.setUserExpenseType(UserExpenseType.PAID);
        userExpenseF.setAmount(1000);
        userExpenseF.setUser(savedUserF);
        UserExpense savedUserExpenseF = userExpenseRepository.save(userExpenseF);

        Expense expense = new Expense();
        expense.setDescription("Total trip expense");
        expense.setAmount(3000);
        expense.setUserExpenses(List.of(savedUserExpenseA, savedUserExpenseB, savedUserExpenseC, savedUserExpenseD, savedUserExpenseE, savedUserExpenseF));
        expense.setCurrency(Currency.INR);
        Expense savedExpense = expenseRepository.save(expense);

        savedGroup.setExpenses(List.of(savedExpense));
        groupRepository.save(savedGroup);
    }
}
