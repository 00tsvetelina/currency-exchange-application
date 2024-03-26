package com.currencyexchangeapp.currencyexchange.service;

import com.currencyexchangeapp.currencyexchange.error.OperationFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ExchangeServiceTest {

    @Mock
    private HttpClient httpClientMock;
    @Mock
    private ExchangeService exchangeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        exchangeService = new ExchangeService(httpClientMock, null);
    }

    @Test
    void testGetExchangeRate_URISyntaxException() throws IOException, InterruptedException {
        when(httpClientMock.send(any(HttpRequest.class), any())).thenThrow(IOException.class);
        assertThrows(OperationFailedException.class, () -> exchangeService.getExchangeRate("USD", "EUR"));
    }
}
