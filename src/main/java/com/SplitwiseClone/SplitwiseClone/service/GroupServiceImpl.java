package com.SplitwiseClone.SplitwiseClone.service;

import com.SplitwiseClone.SplitwiseClone.DTO.TransactionDTO;
import com.SplitwiseClone.SplitwiseClone.exception.GroupNotFoundException;
import com.SplitwiseClone.SplitwiseClone.model.Group;
import com.SplitwiseClone.SplitwiseClone.repository.GroupRepository;
import com.SplitwiseClone.SplitwiseClone.service.strategy.SettleUpStrategy;
import com.SplitwiseClone.SplitwiseClone.service.strategy.SettleUpStrategyFactory;
import com.SplitwiseClone.SplitwiseClone.service.strategy.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService{
    @Autowired
    private GroupRepository groupRepository;
    public List<TransactionDTO> settleUpByGroupId(int groupId) throws GroupNotFoundException {
        SettleUpStrategy settleUpStrategy = SettleUpStrategyFactory.getSettleUpStrategy(Strategy.HEAP_BASED);
        Optional<Group> savedGroup = groupRepository.findById(groupId);
        // Optional is a bucket to avoid null pointer exceptions
        // coz it's not possible that you will always find a group with id=groupId
        // you can get null also, if groupId is wrong
        // That's why return type is Optional
        // It provides us with isEmpty() method, and get() method to return the found object

        if(savedGroup.isEmpty()){
            throw new GroupNotFoundException("Group for given Id is not found! Group id " + groupId);
        }

        List<TransactionDTO> transactions = settleUpStrategy.settleUp(savedGroup.get().getExpenses());

        return transactions;
    }
}
