package com.currencyexchangeapp.currencyexchange.service;

import com.currencyexchangeapp.currencyexchange.model.Conversion;
import com.currencyexchangeapp.currencyexchange.repository.ConversionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ConversionServiceTest {

    @Mock
    private ConversionRepository conversionRepositoryMock;

    @Mock
    private ExchangeService exchangeServiceMock;

    private ConversionService conversionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        conversionService = new ConversionService(conversionRepositoryMock, exchangeServiceMock);
    }

    @Test
    void testConvertCurrency() {
        String baseCurrency = "USD";
        String targetCurrency = "EUR";
        Double amount = 100.0;
        Double exchangeRate = 0.85;
        Double expectedConvertedAmount = exchangeRate * amount;

        when(exchangeServiceMock.getExchangeRate(baseCurrency, targetCurrency)).thenReturn(exchangeRate);
        when(conversionRepositoryMock.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Conversion conversion = conversionService.convertCurrency(baseCurrency, targetCurrency, amount);

        assertEquals(baseCurrency, conversion.getBase());
        assertEquals(targetCurrency, conversion.getSymbol());
        assertEquals(LocalDate.now(), conversion.getDate());
        assertEquals(exchangeRate, conversion.getRate());
        assertEquals(expectedConvertedAmount, conversion.getAmount());
    }

    @Test
    void testGetConversionHistory() {
        LocalDate date = LocalDate.now();
        List<Conversion> conversionList = new ArrayList<>();
        conversionList.add(new Conversion("USD", "EUR", 0.85, 85.0));
        conversionList.add(new Conversion("EUR", "USD", 1.18, 118.0));

        when(conversionRepositoryMock.findAllByDate(date)).thenReturn(conversionList);
        List<Conversion> retrievedConversions = conversionService.getConversionHistory(date);
        assertEquals(conversionList.size(), retrievedConversions.size());
        for (int i = 0; i < conversionList.size(); i++) {
            assertEquals(conversionList.get(i), retrievedConversions.get(i));
        }
    }
}
