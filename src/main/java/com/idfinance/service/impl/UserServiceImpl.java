package com.idfinance.service.impl;

import com.idfinance.dto.UserDto;
import com.idfinance.exception.WrongPostRequestException;
import com.idfinance.model.Coin;
import com.idfinance.model.User;
import com.idfinance.repository.CoinRepository;
import com.idfinance.repository.UserRepository;
import com.idfinance.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CoinRepository coinRepository;
    private static final int SIGNS_AFTER_COMMA = 4;
    private static final double PERCENTAGE_DIFFERENCE = 0.01;
    private static final double ONE_HUNDRED_PERCENTS = 100;
    private static final double ONE_HUNDRED_PERCENTS_LIKE_NUMBER = 1;
    private static final String LOGGER_MESSAGE= "Symbol: %s. Username: %s. Percentage different: %s";

    @Override
    public void saveUser(UserDto userDto) {
        String userName = userDto.getUserName();
        String coinSymbolFromDto = userDto.getCoinSymbol();

        if (coinRepository.existsBySymbol(coinSymbolFromDto)
                && !userRepository.existsByUserNameAndCoinSymbol(userName, coinSymbolFromDto)) {
            Coin coin = coinRepository.getCoinBySymbol(userDto.getCoinSymbol());
            String coinSymbol = coin.getSymbol();
            BigDecimal currentPrice = coin.getPrice();

            userRepository.save(new User(userDto.getUserName(), coinSymbol, currentPrice));
        } else {
            throw new WrongPostRequestException();
        }
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void notifyUsers() {
        List<User> users = getUsers();
        List<Coin> coins = coinRepository.findAll();
        Map<String, BigDecimal> actualPrices = new HashMap<>();

        coins.forEach(coin -> actualPrices.put(coin.getSymbol(), coin.getPrice()));

        users.forEach(currentUser -> {
            BigDecimal coinPrice = currentUser.getCoinPrice();
            BigDecimal actualPrice = actualPrices.get(currentUser.getCoinSymbol());
            BigDecimal ratio = coinPrice.compareTo(actualPrice) > 0
                    ? coinPrice.divide(actualPrice, SIGNS_AFTER_COMMA, RoundingMode.HALF_UP)
                    : actualPrice.divide(coinPrice, SIGNS_AFTER_COMMA, RoundingMode.HALF_UP);
            ratio = ratio.subtract(new BigDecimal(ONE_HUNDRED_PERCENTS_LIKE_NUMBER));

            boolean isDifferentByPercentage = ratio.compareTo(BigDecimal.valueOf(PERCENTAGE_DIFFERENCE)) != 0;

            if (isDifferentByPercentage) {
                log.warn(String.format(LOGGER_MESSAGE,
                        currentUser.getCoinSymbol(),
                        currentUser.getUserName(),
                        ratio.abs().multiply(new BigDecimal(ONE_HUNDRED_PERCENTS))));
            }
        });
    }
}
