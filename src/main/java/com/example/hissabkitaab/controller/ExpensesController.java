package com.example.hissabkitaab.controller;

import com.example.hissabkitaab.payload.ExpensesDto;
import com.example.hissabkitaab.payload.TransactionDto;
import com.example.hissabkitaab.services.ExpensesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/expenses")
public class ExpensesController {
    ExpensesService expensesService;


    public ExpensesController(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }
    @PostMapping("/{groupId}/createExpense")
    public ResponseEntity<ExpensesDto> createExpense(@PathVariable(value = "groupId") long groupId,@RequestBody ExpensesDto expensesDto)
    {
        ExpensesDto newExpense= expensesService.createExpense(groupId,expensesDto);
        return new ResponseEntity<>(newExpense, HttpStatus.CREATED);

    }
    @PostMapping("/{groupId}/{expenseId}/addTransaction")
    public ResponseEntity<TransactionDto> addTransaction(@PathVariable(value = "groupId") long groupId,@PathVariable(value = "expenseId") long expenseId,@RequestBody TransactionDto transactionDto)
    {
        TransactionDto newTransaction= expensesService.addTransaction(groupId,expenseId,transactionDto);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);

    }
    @GetMapping("/{groupId}/allExpenses")
    public List<ExpensesDto> getExpensesByTripId(@PathVariable(value = "groupId") long groupId)
    {
        return expensesService.getExpensesByTripId(groupId);

    }
    @GetMapping("/{expenseId}/allTransactions")
    public List<TransactionDto> getTransactionByExpenseId(@PathVariable(value = "expenseId") long expenseId)
    {
        return expensesService.getTransactionByExpenseId(expenseId);

    }

    @GetMapping("/{groupId}/expense/{id}")
    public ResponseEntity<ExpensesDto> getExpenseById(@PathVariable(value = "groupId") Long groupId,@PathVariable(value = "id") Long expenseId)
    {
        ExpensesDto expenseDto = expensesService.getExpenseById(groupId,expenseId);
        return new ResponseEntity<>(expenseDto,HttpStatus.OK);

    }
    @PutMapping("/{groupId}/{expenseId}/{transactionId}/updateTransaction")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable(value = "groupId")Long groupId, @PathVariable(value = "expenseId")Long expenseId,@PathVariable(value = "transactionId")Long transactionId,@RequestBody TransactionDto expenseRequest)
    {
        return new ResponseEntity<>(expensesService.updateTransaction(groupId,expenseId,transactionId,expenseRequest),HttpStatus.OK);

    }
    @PutMapping("/{groupId}/{expenseId}/updateExpense")
    public ResponseEntity<ExpensesDto> updateExpense(@PathVariable(value = "groupId") Long groupId, @PathVariable(value = "expenseId") Long expenseId, @RequestBody ExpensesDto expenseRequest)
    {
        return new ResponseEntity<>(expensesService.updateExpense(groupId,expenseId,expenseRequest),HttpStatus.OK);

    }
}
