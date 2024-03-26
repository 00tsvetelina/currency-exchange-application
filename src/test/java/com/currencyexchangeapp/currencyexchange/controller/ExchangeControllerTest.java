package com.currencyexchangeapp.currencyexchange.controller;

import com.currencyexchangeapp.currencyexchange.service.ExchangeService;
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
    @InjectMocks
    ExchangeController exchangeController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetExchangeRate() {
        String base = "USD";
        String symbol = "EUR";
        Double exchangeRate = 0.85;
        when(exchangeService.getExchangeRate(base, symbol)).thenReturn(exchangeRate);

        ResponseEntity<Double> response = exchangeController.getExchangeRate(base, symbol);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(exchangeRate, response.getBody());
        verify(exchangeService, times(1)).getExchangeRate(base, symbol);
    }
}
