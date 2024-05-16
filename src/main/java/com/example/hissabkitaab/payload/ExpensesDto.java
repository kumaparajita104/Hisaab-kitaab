package com.example.hissabkitaab.payload;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ExpensesDto {
    Long id;
    String name;

    Long userId;
    int f=0;
    private Long groupId;
    List<TransactionDto> transaction;

}
