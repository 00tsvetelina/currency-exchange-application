package com.currencyexchangeapp.currencyexchange.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.AnnotatedArrayType;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Conversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "base")
    private String base;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "amount")
    private Double amount;

    public Conversion(String base, String symbol, Double rate, Double amount) {
        this.base = base;
        this.symbol = symbol;
        this.date = LocalDateTime.now();
        this.rate = rate;
        this.amount = amount;
    }
}
