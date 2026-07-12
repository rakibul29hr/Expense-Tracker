package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.Dto.CreateUserDto;
import com.example.ExpenseTracker.Dto.LogInDto;
import com.example.ExpenseTracker.Dto.UserDto;
import com.example.ExpenseTracker.entity.User;
import com.example.ExpenseTracker.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoded;
    private UserRepository userRepository;

    public UserDto createUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setName(createUserDto.getName());
        user.setEmail(createUserDto.getEmail());
        user.setPassword(passwordEncoded.encode(createUserDto.getPassword()));
       User savedUser= userRepository.save(user);
       UserDto userDto= new UserDto();
       userDto.setId(savedUser.getId());
       userDto.setName(savedUser.getName());
       userDto.setEmail(savedUser.getEmail());
       return userDto;
    }

    public String loginUser(LogInDto logInDto) {
        User user = userRepository.findByEmail(logInDto.getEmail()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));

        if(!passwordEncoded.matches(logInDto.getPassword(),user.getPassword())){
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Incorrect Password");
        }
        return "Login Successful";
    }


}
