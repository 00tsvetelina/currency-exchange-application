package com.currencyexchangeapp.currencyexchange.controller;

import com.currencyexchangeapp.currencyexchange.service.ExchangeService;
import io.github.bucket4j.Bucket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ExchangeControllerTest {

    @Mock
    ExchangeService exchangeService;

    @Mock
    Bucket bucket;

    @InjectMocks
    ExchangeController exchangeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetExchangeRate_Success() {
        String base = "USD";
        String symbol = "EUR";
        Double exchangeRate = 0.85;
        when(exchangeService.getExchangeRate(base, symbol)).thenReturn(exchangeRate);
        when(bucket.tryConsume(1)).thenReturn(true);

        ResponseEntity<Double> response = exchangeController.getExchangeRate(base, symbol);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(exchangeRate, response.getBody());
        verify(exchangeService, times(1)).getExchangeRate(base, symbol);
    }

    @Test
    void testGetExchangeRate_TooManyRequests() {
        String base = "USD";
        String symbol = "EUR";
        when(bucket.tryConsume(1)).thenReturn(false);

        ResponseEntity<Double> response = exchangeController.getExchangeRate(base, symbol);
        assertEquals(HttpStatus.TOO_MANY_REQUESTS, response.getStatusCode());
        verify(exchangeService, never()).getExchangeRate(base, symbol);
    }
}
