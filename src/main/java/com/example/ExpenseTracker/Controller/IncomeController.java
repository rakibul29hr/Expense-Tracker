package com.example.ExpenseTracker.Controller;

import com.example.ExpenseTracker.Dto.CreateExpenseDto;
import com.example.ExpenseTracker.Dto.CreateIncomeDto;
import com.example.ExpenseTracker.Dto.RCExpenseDto;
import com.example.ExpenseTracker.Dto.RCIncomeDto;
import com.example.ExpenseTracker.service.IncomeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users/{userId}/income")
public class IncomeController {
    private final IncomeService incomeService;



    @PostMapping
    public ResponseEntity<RCIncomeDto> addIncome(
            @PathVariable Long userId,
            @RequestBody CreateIncomeDto income) {
        return ResponseEntity.status(HttpStatus.OK).body(incomeService.createIncome(userId,income));
    }

    @GetMapping
    public ResponseEntity<List<RCIncomeDto>> findAllExpenses(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(incomeService.getAllExpenses(userId));
    }


    @GetMapping("/{incomeId}")
    public ResponseEntity<RCIncomeDto> findById(@PathVariable Long userId, @PathVariable Long incomeId) {
        return ResponseEntity.status
                (HttpStatus.OK)
                .body(incomeService.
                        getIncomeById(userId,incomeId   ));
    }

    @PutMapping("/{incomeId}")
    public ResponseEntity<RCIncomeDto> updateExpense(
            @PathVariable Long userId,
            @PathVariable Long incomeId,
            @RequestBody CreateIncomeDto dto) {

        return ResponseEntity.status(HttpStatus.OK).
                body(incomeService.updateIncome(userId, incomeId, dto));
    }

    @DeleteMapping("/{incomeId}")
    public ResponseEntity<Void> deleteExpense(
            @PathVariable Long userId,
            @PathVariable Long incomeId) {
        incomeService.deleteIncome(userId, incomeId);
        return ResponseEntity.noContent().build()    ;
    }

}
