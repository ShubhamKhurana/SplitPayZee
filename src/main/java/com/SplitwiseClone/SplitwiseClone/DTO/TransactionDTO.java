package com.SplitwiseClone.SplitwiseClone.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private String fromUserName;
    private String toUserName;
    private double amount;
}
