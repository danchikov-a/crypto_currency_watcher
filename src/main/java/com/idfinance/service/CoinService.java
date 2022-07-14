package com.idfinance.service;

import com.idfinance.model.Coin;

import java.util.List;

public interface CoinService {
    List<Coin> getCoinList();
    void saveCoin(int coinNumber);
}
