package com.example.ExpenseTracker.Controller;

import com.example.ExpenseTracker.Dto.CreateUserDto;
import com.example.ExpenseTracker.Dto.UserDto;
import com.example.ExpenseTracker.service.UserService;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @GetMapping("/login")
    public ResponseEntity<UserDto> login(@RequestParam String username, @RequestParam String password){
        return ResponseEntity.status()
    }

}
