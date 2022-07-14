package com.idfinance.controller;

import com.idfinance.model.Coin;
import com.idfinance.service.impl.CoinServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoinController {
    private CoinServiceImpl coinServiceImpl;

    public CoinController(CoinServiceImpl coinServiceImpl) {
        this.coinServiceImpl = coinServiceImpl;
    }

    @GetMapping("/coins")
    public ResponseEntity<List<Coin>> getCoins() {
        return ResponseEntity.ok().body(coinServiceImpl.getCoinList());
    }
}
