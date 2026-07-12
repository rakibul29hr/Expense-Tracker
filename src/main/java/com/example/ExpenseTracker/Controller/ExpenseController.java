package com.example.ExpenseTracker.Controller;

import com.example.ExpenseTracker.Dto.CreateExpenseDto;
import com.example.ExpenseTracker.entity.Expense;
import com.example.ExpenseTracker.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users/{userId}/expense")
public class ExpenseController {

    private ExpenseService expenseService;
    @PostMapping
    public ResponseEntity<Expense> addExpense(@PathVariable Long userId, @RequestBody CreateExpenseDto expense) {
        return ResponseEntity.status(HttpStatus.OK).body(expenseService.createExpense(userId,expense));
    }
}
