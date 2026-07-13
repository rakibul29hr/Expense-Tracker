package com.example.ExpenseTracker.service;


import com.example.ExpenseTracker.Dto.CreateIncomeDto;
import com.example.ExpenseTracker.Dto.RCIncomeDto;
import com.example.ExpenseTracker.entity.Income;
import com.example.ExpenseTracker.entity.User;
import com.example.ExpenseTracker.repository.IncomeRepository;
import com.example.ExpenseTracker.repository.UserRepository;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
public class IncomeService {

    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;

    public RCIncomeDto mapToDto(Income income) {

        RCIncomeDto dto = new RCIncomeDto();

        dto.setId(income.getId());
        dto.setAmount(income.getAmount());
        dto.setDescription(income.getDescription());
        dto.setDate(income.getDate());
        dto.setUserId(income.getUser().getId());

        return dto;
    }


    public RCIncomeDto createIncome(Long userId, CreateIncomeDto dto)
        {

        User user = userRepository.findById(userId).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User not found"));
       Income income = new Income();
        income.setAmount(dto.getAmount());

        income.setDescription(dto.getDescription());
        income.setDate(dto.getDate());
        income.setUser(user);


        Income returnI= incomeRepository.save(income);
        RCIncomeDto response = new RCIncomeDto();
        response.setId(returnI.getId());
        response.setAmount(returnI.getAmount());
        response.setDescription(returnI.getDescription());
        response.setDate(returnI.getDate());
        response.setUserId(user.getId());
        return response;
    }

    public List<RCIncomeDto> getAllExpenses(Long userId) {
        List<Income> incomes= incomeRepository.findByUserId(userId);
        return incomes.stream()
                .map(this::mapToDto)
                .toList();
    }



    public RCIncomeDto getIncomeById(Long userId, Long incomeId) {

        return mapToDto(
                incomeRepository.findByIdAndUserId(incomeId, userId)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND
                                ,"Income or User not found"))
        );

    }

    public RCIncomeDto updateIncome(Long userId,
                                      Long incomeId,
                                      CreateIncomeDto dto) {

        Income income    = incomeRepository
                .findByIdAndUserId(incomeId, userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Income or User not found"));

        income.setAmount(dto.getAmount());
        income.setDescription(dto.getDescription());
        income.setDate(dto.getDate());

        return mapToDto(incomeRepository.save(income));
    }


    public void deleteIncome (Long userId, Long incomeId) {

        Income income    = incomeRepository
                .findByIdAndUserId(incomeId, userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Expense not found"));
        incomeRepository.delete(income);
    }

}
