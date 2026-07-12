package com.example.ExpenseTracker.Dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CreateExpenseDto {

    private BigDecimal amount;
    private String description;
    private String category;
    private LocalDate date;
}
