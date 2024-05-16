package com.example.hissabkitaab.services;

import com.example.hissabkitaab.payload.ExpensesDto;
import com.example.hissabkitaab.payload.TransactionDto;

import java.util.List;

public interface ExpensesService {
    ExpensesDto createExpense(long tripId,ExpensesDto expenseDto);
    TransactionDto addTransaction(long tripId, long expenseId,TransactionDto expenseDto);

    List<ExpensesDto> getExpensesByTripId(long tripId);

    ExpensesDto getExpenseById(long tripId,long expenseId);
    List<TransactionDto> getTransactionByExpenseId(long expenseId);

    TransactionDto updateTransaction(Long tripId, Long expenseId, Long TransactionId,TransactionDto expenseRequest);
    ExpensesDto updateExpense(Long tripId, Long expenseId,ExpensesDto expenseRequest);

    void deleteExpense(Long tripId,Long expenseId);

}
