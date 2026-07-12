package com.example.ExpenseTracker.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateUserDto {
    private String name;
    private String email;
    private String password;
}
