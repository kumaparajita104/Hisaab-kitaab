package com.example.hissabkitaab.repositories;

import com.example.hissabkitaab.entity.Expenses;
import com.example.hissabkitaab.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByExpenseId(long tripId);

}
