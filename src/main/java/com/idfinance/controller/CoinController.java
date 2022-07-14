package com.idfinance.controller;

import com.idfinance.model.Coin;
import com.idfinance.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CoinController {
    private final CoinService coinService;
    private static final String ERROR_MESSAGE = "There isn't such crypto";

    @GetMapping("/coins")
    public ResponseEntity<List<Coin>> getCoins() {
        return ResponseEntity.ok().body(coinService.getCoinList());
    }

    @GetMapping("/coins/{id}")
    public ResponseEntity<String> getCoinPrice(@PathVariable Long id) {
        try {
            Coin coin = coinService.getCoin(id);
            return ResponseEntity.ok().body(coin.getPrice().toString());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.ok().body(ERROR_MESSAGE);
        }
    }
}
