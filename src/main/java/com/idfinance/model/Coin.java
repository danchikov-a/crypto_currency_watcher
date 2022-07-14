package com.idfinance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
public class Coin {
    @Id
    private Long id;
    private String symbol;
    @JsonIgnore
    private BigDecimal price;

    public Coin(Long id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public Coin(Long id, String symbol, BigDecimal price) {
        this.id = id;
        this.symbol = symbol;
        this.price = price;
    }
}
