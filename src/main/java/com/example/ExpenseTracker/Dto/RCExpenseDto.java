package com.example.ExpenseTracker.Dto;

import com.example.ExpenseTracker.entity.Expense;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class RCExpenseDto {
    private Long id;
    private BigDecimal amount;
    private String description;
    private String category;
    private LocalDate date;
    private Long userId;


}
