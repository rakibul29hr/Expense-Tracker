package com.example.ExpenseTracker.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
public class CreateIncomeDto {
    public BigDecimal amount;
    public String description;
    public LocalDate date;
}
