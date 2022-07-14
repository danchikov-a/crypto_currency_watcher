package com.idfinance.controller;

import com.idfinance.dto.UserDto;
import com.idfinance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/notify")
    public void createNotification(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
    }
}
