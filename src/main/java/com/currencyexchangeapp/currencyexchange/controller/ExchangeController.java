package com.currencyexchangeapp.currencyexchange.controller;

import com.currencyexchangeapp.currencyexchange.service.ExchangeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping("/exchangeRate")
    public ResponseEntity<Double> getExchangeRate(@RequestParam String base, String symbol) {
        return ResponseEntity.ok(exchangeService.getExchangeRate(base, symbol));
    }

}
