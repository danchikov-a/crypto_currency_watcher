package com.idfinance.service;

import com.idfinance.model.Coin;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CoinService {
    List<Coin> getCoinList();
    void saveCoin(int coinNumber);
    Coin getCoin(Long id);
}
