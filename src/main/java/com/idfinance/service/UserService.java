package com.idfinance.service;

import com.idfinance.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUser(UserDto userDto);
}
