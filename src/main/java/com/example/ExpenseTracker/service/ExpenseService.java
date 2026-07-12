package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.Dto.CreateExpenseDto;
import com.example.ExpenseTracker.entity.Expense;
import com.example.ExpenseTracker.entity.User;
import com.example.ExpenseTracker.repository.ExpenseRepository;
import com.example.ExpenseTracker.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    private UserRepository userRepository;
    public Expense createExpense(Long userId, CreateExpenseDto createExpenseDto) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Expense expense = new Expense();
        expense.setAmount(createExpenseDto.getAmount());
        expense.setCategory(createExpenseDto.getCategory());
        expense.setDescription(createExpenseDto.getDescription());
        expense.setDate(createExpenseDto.getDate());
        expense.setUser(user);
        return expenseRepository.save(expense);
    }
}
