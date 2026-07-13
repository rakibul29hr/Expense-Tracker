package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.Dto.CreateExpenseDto;
import com.example.ExpenseTracker.Dto.RCExpenseDto;
import com.example.ExpenseTracker.entity.Expense;
import com.example.ExpenseTracker.entity.User;
import com.example.ExpenseTracker.repository.ExpenseRepository;
import com.example.ExpenseTracker.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    private RCExpenseDto mapToDto(Expense expense) {

        RCExpenseDto dto = new RCExpenseDto();

        dto.setId(expense.getId());
        dto.setAmount(expense.getAmount());
        dto.setDescription(expense.getDescription());
        dto.setCategory(expense.getCategory());
        dto.setDate(expense.getDate());
        dto.setUserId(expense.getUser().getId());

        return dto;
    }


    public RCExpenseDto createExpense(Long userId, CreateExpenseDto createExpenseDto) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Expense expense = new Expense();
        expense.setAmount(createExpenseDto.getAmount());
        expense.setCategory(createExpenseDto.getCategory());
        expense.setDescription(createExpenseDto.getDescription());
        expense.setDate(createExpenseDto.getDate());
        expense.setUser(user);
        Expense ex= expenseRepository.save(expense);
        RCExpenseDto response = new RCExpenseDto();
        response.setId(ex.getId());
        response.setAmount(ex.getAmount());
        response.setCategory(ex.getCategory());
        response.setDescription(ex.getDescription());
        response.setDate(ex.getDate());
        response.setUserId(user.getId());
        return response;
    }

//Get All Expense
    public List<RCExpenseDto> getAllExpenses(Long userId) {
        List<Expense> expenses= expenseRepository.findByUserId(userId);
        return expenses.stream()
                .map(this::mapToDto)
                .toList();
    }

    public RCExpenseDto getExpenseById(Long userId, Long expenseId) {

        return mapToDto(
                expenseRepository.findByIdAndUserId(expenseId, userId)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Expense not found"))
        );
    }

    public RCExpenseDto updateExpense(Long userId,
                                 Long expenseId,
                                 CreateExpenseDto dto) {

        Expense expense = expenseRepository
                .findByIdAndUserId(expenseId, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Expense or User not found"));

        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());
        expense.setDescription(dto.getDescription());
        expense.setDate(dto.getDate());

        return mapToDto(expenseRepository.save(expense));
    }

    public void deleteExpense(Long userId, Long expenseId) {

        Expense expense = expenseRepository
                .findByIdAndUserId(expenseId, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Expense not found"));
        expenseRepository.delete(expense);
    }
}
