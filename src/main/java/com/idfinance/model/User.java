package com.idfinance.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String coinSymbol;
    private BigDecimal coinPrice;

    public User(String userName, String coinSymbol, BigDecimal coinPrice) {
        this.userName = userName;
        this.coinSymbol = coinSymbol;
        this.coinPrice = coinPrice;
    }
}
