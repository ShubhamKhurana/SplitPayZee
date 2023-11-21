package com.SplitwiseClone.SplitwiseClone.service;

import com.SplitwiseClone.SplitwiseClone.DTO.TransactionDTO;
import com.SplitwiseClone.SplitwiseClone.exception.GroupNotFoundException;

import java.util.List;

public interface GroupService {
    List<TransactionDTO> settleUpByGroupId(int groupId) throws GroupNotFoundException;
}
