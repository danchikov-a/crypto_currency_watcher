package com.idfinance.model;

import lombok.Data;

@Data
public class Coin {
    private Long id;
    private String symbol;

    public Coin(Long id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }
}
