package com.example.hissabkitaab.repositories;

import com.example.hissabkitaab.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expenses,Long> {
    List<Expenses> findByGroupId(long tripId);
}
