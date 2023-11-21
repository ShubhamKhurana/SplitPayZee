package com.SplitwiseClone.SplitwiseClone.service.strategy;

import com.SplitwiseClone.SplitwiseClone.DTO.TransactionDTO;
import com.SplitwiseClone.SplitwiseClone.model.Expense;
import com.SplitwiseClone.SplitwiseClone.model.User;
import com.SplitwiseClone.SplitwiseClone.model.UserExpense;
import com.SplitwiseClone.SplitwiseClone.model.UserExpenseType;
import jakarta.annotation.Priority;

import java.nio.DoubleBuffer;
import java.util.*;

public class HeapBasedSettledUpStrategy implements SettleUpStrategy{
    public List<TransactionDTO> settleUp(List<Expense> expenses){
        Map<User, Double> outstandingAmountMap = new HashMap<>();
        List<TransactionDTO> transactions = new ArrayList<>();

        // Calculate Outstanding Amounts for each user
        for(Expense expense : expenses){
            // each expense will have list of UserExpenses
            for(UserExpense userExpense : expense.getUserExpenses()){
                User user = userExpense.getUser();

                double currentOutStandingAmount = outstandingAmountMap.getOrDefault(user, 0.00);

                if(userExpense.getUserExpenseType().equals(UserExpenseType.PAID)){
                    currentOutStandingAmount += userExpense.getAmount();
                }
                else{
                    currentOutStandingAmount -= userExpense.getAmount();
                }

                outstandingAmountMap.put(user, currentOutStandingAmount);
            }
        }

        // Build PriorityQueues
        // You can store pair also
        // But to ease up the process, picking up the entry directly from the map

        // In Java, pq is by default MIN-HEAP
        PriorityQueue< Map.Entry<User, Double> > maxHeap = new PriorityQueue<>(
                // Let's write Comparator as Lambda
                // (a, b) -> (int) (a.getValue() - b.getValue())
                 (a, b) -> Double.compare(b.getValue(), a.getValue())
        );

        PriorityQueue< Map.Entry<User, Double> > minHeap = new PriorityQueue<>(
                 (a, b) -> Double.compare(a.getValue(), b.getValue())
        );

        // Now, let's populate the heaps using Maps
        for(Map.Entry<User, Double> entry : outstandingAmountMap.entrySet()){
            if(entry.getValue() < 0){
                minHeap.add(entry);
            }
            else if(entry.getValue() > 0){
                maxHeap.add(entry);
            }
            else{
                System.out.println("\n" + entry.getKey().getName() + " is already settled up");
            }
        }

        // Calculate the Transactions until the heaps become empty
        while(!minHeap.isEmpty()){
            Map.Entry<User, Double> maxHasToPay = minHeap.poll();
            Map.Entry<User, Double> maxWillGetPaid = maxHeap.poll();

            double amountPaid = Math.min(Math.abs(maxHasToPay.getValue()), maxWillGetPaid.getValue());

            // let's create TransactionDTO, to gather info about who paid who, and how much
            TransactionDTO transactionDTO = new TransactionDTO(maxHasToPay.getKey().getName(), maxWillGetPaid.getKey().getName(), amountPaid);
            transactions.add(transactionDTO);

            double newBalance = maxHasToPay.getValue() + maxWillGetPaid.getValue(); // (-ve) + (+ve)

            if(newBalance > 0){
                // still need to be paid more to this guy
                maxWillGetPaid.setValue(newBalance);
                maxHeap.add(maxWillGetPaid);
            }
            else if(newBalance < 0){
                // this guy still needs to pay more to the group
                maxHasToPay.setValue(newBalance);
                minHeap.add(maxHasToPay);
            }
            // for newBalance == 0, nothing goes to heaps, as that transaction will get settles for both
        }

        return transactions;
    }
}
