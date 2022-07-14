package com.idfinance.schedule;

import com.idfinance.service.CoinService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class Schedule {
    private static final int ONE_MINUTE = 60_000;
    private CoinService coinService;

    public Schedule(CoinService coinService) {
        this.coinService = coinService;
    }

    @Scheduled(fixedRate = ONE_MINUTE)
    public void savePrice() {
        IntStream.range(0, coinService.getCoinList().size())
                .forEach(i -> coinService.saveCoin(i));
    }
}
