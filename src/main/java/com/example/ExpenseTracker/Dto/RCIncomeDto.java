package com.example.ExpenseTracker.Dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class RCIncomeDto {
    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private Long userId;
}
