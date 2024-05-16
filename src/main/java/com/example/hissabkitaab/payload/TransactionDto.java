package com.example.hissabkitaab.payload;

import lombok.Data;

@Data
public class TransactionDto {
    Long id;
    String payee;
    Long amountPaid;
    int f=0;
    Long userId;
}
