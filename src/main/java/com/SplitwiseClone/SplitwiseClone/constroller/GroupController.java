package com.SplitwiseClone.SplitwiseClone.constroller;

import com.SplitwiseClone.SplitwiseClone.DTO.TransactionDTO;
import com.SplitwiseClone.SplitwiseClone.exception.GroupNotFoundException;
import com.SplitwiseClone.SplitwiseClone.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {
    @Autowired
    private GroupService groupService;

    // Create an API
    @GetMapping("/settleUp/{groupId}") // to set up the "GET" route
    // Also, argument variable should be same as provided in GETMAPPING ANNOTATION
    public ResponseEntity settleUpForGroup(@PathVariable("groupId") int groupId) throws GroupNotFoundException {
        List<TransactionDTO> transactions = groupService.settleUpByGroupId(groupId);
        return ResponseEntity.ok(transactions);
    }

    // Response Entity returns (HTTP Status Code & Object)
}
