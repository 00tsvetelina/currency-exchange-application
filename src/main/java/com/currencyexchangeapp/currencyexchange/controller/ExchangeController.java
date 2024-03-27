package com.currencyexchangeapp.currencyexchange.controller;

import com.currencyexchangeapp.currencyexchange.service.ExchangeService;
import io.github.bucket4j.Bucket;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@AllArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;
    private final Bucket bucket;

    @GetMapping("/exchangeRate")
    public ResponseEntity<Double> getExchangeRate(@RequestParam String base, String symbol) {
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok(exchangeService.getExchangeRate(base, symbol));
        }

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

}
