package com.idfinance.service;

import com.idfinance.dto.UserDto;
import com.idfinance.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void saveUser(UserDto userDto);
    List<User> getUsers();
    void notifyUsers();
}
