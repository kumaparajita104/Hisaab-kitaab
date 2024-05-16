package com.example.hissabkitaab.services.Impl;

import com.example.hissabkitaab.entity.*;
import com.example.hissabkitaab.exception.PostCommentException;
import com.example.hissabkitaab.exception.ResourceNotFoundException;
import com.example.hissabkitaab.payload.ExpensesDto;
import com.example.hissabkitaab.payload.TransactionDto;
import com.example.hissabkitaab.repositories.*;
import com.example.hissabkitaab.services.ExpensesService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ExpenseServiceImpl implements ExpensesService {

    private ExpenseRepository expenseRepository;
    private TripRepository tripRepository;

    private UserRepository userRepository;

    private TransactionRepository transactionRepository;
    private GroupRepository groupRepository;

    ModelMapper mapper;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, TripRepository tripRepository, GroupRepository groupRepository,TransactionRepository transactionRepository,UserRepository userRepository,ModelMapper mapper) {
        this.expenseRepository = expenseRepository;
        this.tripRepository = tripRepository;
        this.userRepository=userRepository;
        this.transactionRepository=transactionRepository;
        this.groupRepository=groupRepository;
        this.mapper = mapper;
    }
    @Override
    public ExpensesDto createExpense(long groupId, ExpensesDto expenseDto) {

        Expenses expense = mapToEntity(expenseDto);
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("Trip","Id",groupId));

        //comment.setCreatedAt(LocalDateTime.now());
        expense.setGroup(group);

        Expenses newExpense = expenseRepository.save(expense);
        return maptoDto(newExpense);

    }

    @Override
    public TransactionDto addTransaction(long tripId,long expenseId,TransactionDto transactionDto) {

        Transaction transaction = mapToEntity(transactionDto);
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new ResourceNotFoundException("Trip","Id",tripId));
        User user= userRepository.findById(transaction.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User","Id",transaction.getUserId()));
        Expenses expense= expenseRepository.findById(expenseId).orElseThrow(()-> new ResourceNotFoundException("Expense","Id",expenseId));
        //comment.setCreatedAt(LocalDateTime.now());

        //transaction.setUserId(userId);
        transaction.setExpense(expense);

        Transaction newTransaction = transactionRepository.save(transaction);
        return maptoDto(newTransaction);

    }
    @Override
    public List<TransactionDto> getTransactionByExpenseId(long expenseId) {
        List<Transaction> transactions=transactionRepository.findByExpenseId(expenseId);
        return transactions.stream().map(expenses1 -> maptoDto(expenses1)).collect(Collectors.toList());

    }

    @Override
    public List<ExpensesDto> getExpensesByTripId(long groupId) {
        List<Expenses> expenses=expenseRepository.findByGroupId(groupId);
        return expenses.stream().map(expenses1 -> maptoDto(expenses1)).collect(Collectors.toList());

    }

    @Override
    public ExpensesDto getExpenseById(long groupId, long expenseId) {
        Group group=groupRepository.findById(groupId).orElseThrow(()->new ResourceNotFoundException("Trip","id",groupId));
        Expenses expense=expenseRepository.findById(expenseId).orElseThrow(() -> new ResourceNotFoundException("Expense","Id",expenseId));

        if(!expense.getGroup().getId().equals(group.getId()))
        {
            throw new PostCommentException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        return maptoDto(expense);
    }

    @Override
    public ExpensesDto updateExpense(Long groupId, Long expenseId, ExpensesDto expenseRequest) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("Trip","Id",groupId));
        Expenses expense=expenseRepository.findById(expenseId).orElseThrow(() -> new ResourceNotFoundException("Expense","Id",expenseId));
        if(!expense.getGroup().getId().equals(group.getId()))
        {
            throw new PostCommentException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }

        expense.setName(expenseRequest.getName());
        expense.setF(expenseRequest.getF());



        //expense.setTrip(trip);

        Expenses updatedExpense=expenseRepository.save(expense);
        return maptoDto(updatedExpense);
    }
    @Override
    public TransactionDto updateTransaction(Long groupId, Long expenseId,Long transactionId,TransactionDto transactionRequest) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("Trip","Id",groupId));
        Expenses expense=expenseRepository.findById(expenseId).orElseThrow(() -> new ResourceNotFoundException("Expense","Id",expenseId));
        Transaction transaction=transactionRepository.findById(transactionId).orElseThrow(()->new ResourceNotFoundException("Transaction","id",transactionId));
//        if(!expense.getTrip().getId().equals(trip.getId()))
//        {
//            throw new PostCommentException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
//        }

        transaction.setPayee(transactionRequest.getPayee());
        transaction.setAmountPaid(transactionRequest.getAmountPaid());
        transaction.setUserId(transactionRequest.getUserId());
        transaction.setF(transactionRequest.getF());
        System.out.println(transactionRequest.getPayee());





        Transaction updatedTransaction=transactionRepository.save(transaction);
        return maptoDto(transaction);
    }

    @Override
    public void deleteExpense(Long tripId, Long expenseId) {


    }
    private TransactionDto maptoDto(Transaction transaction)
    {
        TransactionDto transactionDto=mapper.map(transaction, TransactionDto.class);
        /*CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        commentDto.setCreatedAt(LocalDateTime.now());*/
        return transactionDto;
    }
    private ExpensesDto maptoDto(Expenses expense)
    {
        ExpensesDto expenseDto=mapper.map(expense, ExpensesDto.class);
        /*CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        commentDto.setCreatedAt(LocalDateTime.now());*/
        return expenseDto;
    }

    private Transaction mapToEntity(TransactionDto transactionDto)
    {
        Transaction transaction = mapper.map(transactionDto,Transaction.class);
        /*Comment comment=new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setCreatedAt(LocalDateTime.now());*/
        return transaction;
    }
    private Expenses mapToEntity(ExpensesDto expenseDto)
    {
        Expenses expense = mapper.map(expenseDto,Expenses.class);
        /*Comment comment=new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setCreatedAt(LocalDateTime.now());*/
        return expense;
    }
}
