package com.idfinance.service.impl;

import com.idfinance.dto.UserDto;
import com.idfinance.model.Coin;
import com.idfinance.model.User;
import com.idfinance.repository.CoinRepository;
import com.idfinance.repository.UserRepository;
import com.idfinance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CoinRepository coinRepository;

    @Override
    public void saveUser(UserDto userDto) {
        Coin coin = coinRepository.getCoinBySymbol(userDto.getCoinSymbol());
        Long coinId = coin.getId();
        BigDecimal currentPrice = coin.getPrice();

        userRepository.save(new User(userDto.getUserName(), coinId, currentPrice));
    }
}
