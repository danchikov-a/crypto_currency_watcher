package com.idfinance.schedule;

import com.idfinance.model.Coin;
import com.idfinance.service.CoinService;
import com.idfinance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class Schedule {
    private static final int ONE_MINUTE = 60_000;
    private final CoinService coinService;
    private final UserService userService;

    @Scheduled(fixedRate = ONE_MINUTE)
    public void savePrice() {
        List<Coin> coins = coinService.getCoinList();

        IntStream.range(0, coins.size()).forEach(coinService::saveCoin);

        userService.notifyUsers();
    }
}
