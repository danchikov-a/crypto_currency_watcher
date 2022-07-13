package com.idfinance.controller;

import com.idfinance.model.Coin;
import com.idfinance.service.CoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoinController {
    private CoinService coinService;

    public CoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping("/coins")
    public ResponseEntity<List<Coin>> getCoins() {
        return ResponseEntity.ok().body(coinService.getCoinList());
    }
}
