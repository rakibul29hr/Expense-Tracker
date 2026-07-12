package com.example.ExpenseTracker.Controller;

import com.example.ExpenseTracker.Dto.CreateExpenseDto;
import com.example.ExpenseTracker.Dto.RCExpenseDto;
import com.example.ExpenseTracker.entity.Expense;
import com.example.ExpenseTracker.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/users/{userId}/expense")
public class ExpenseController {

    private final ExpenseService expenseService;
    @PostMapping
    public ResponseEntity<RCExpenseDto> addExpense(@PathVariable Long userId, @RequestBody CreateExpenseDto expense) {
        return ResponseEntity.status(HttpStatus.OK).body(expenseService.createExpense(userId,expense));
    }

    @GetMapping
    public ResponseEntity<List<RCExpenseDto>> findAllExpenses(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(expenseService.getAllExpenses(userId));
    }
}
