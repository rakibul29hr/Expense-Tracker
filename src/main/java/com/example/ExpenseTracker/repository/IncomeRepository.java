package com.example.ExpenseTracker.repository;

import com.example.ExpenseTracker.entity.Expense;
import com.example.ExpenseTracker.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface  IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByUserId(Long userId);

    Optional<Expense> findByIdAndUserId(Long expenseId, Long userId);
}
