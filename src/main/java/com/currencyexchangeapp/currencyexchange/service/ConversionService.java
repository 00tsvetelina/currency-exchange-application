package com.currencyexchangeapp.currencyexchange.service;

import com.currencyexchangeapp.currencyexchange.model.Conversion;
import com.currencyexchangeapp.currencyexchange.repository.ConversionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConversionService {

    private final ConversionRepository conversionRepository;
    private final ExchangeService exchangeService;

    // POST
    // add convertCurrency method
    @Transactional
    public Conversion convertCurrency(String base, String symbol, Double amount) {
        // get exchange rate
        Double exchangeRate = exchangeService.getExchangeRate(base, symbol);

        // multiply amount by exchange rate
        Double convertedAmount = exchangeRate * amount;

        // create a conversion obj
        Conversion conversion = new Conversion(base, symbol, exchangeRate, convertedAmount);

        // save conversion
        return conversionRepository.save(conversion);
    }

    // GET
    // add getConversionHistoryList method

}
