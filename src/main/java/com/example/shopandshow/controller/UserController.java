package com.example.shopandshow.controller;

import com.example.shopandshow.persistence.dto.UserDTO;
import com.example.shopandshow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO.Result create(@RequestBody UserDTO.Create createDto) {
        return userService.create(createDto);
    }

    @GetMapping("/login")
    public UserDTO.Result login(@RequestParam String name,
                                @RequestParam String password) {
        return userService.login(name, password);
    }
}
