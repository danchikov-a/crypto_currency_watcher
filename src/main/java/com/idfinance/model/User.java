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
    private Long coinId;
    private BigDecimal coinPrice;

    public User(String userName, Long coinId, BigDecimal coinPrice) {
        this.userName = userName;
        this.coinId = coinId;
        this.coinPrice = coinPrice;
    }
}
