package com.currencyexchangeapp.currencyexchange.service;

import com.currencyexchangeapp.currencyexchange.model.Conversion;
import com.currencyexchangeapp.currencyexchange.repository.ConversionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ConversionService {

    private final ConversionRepository conversionRepository;
    private final ExchangeService exchangeService;

    @Transactional
    public Conversion convertCurrency(String base, String symbol, Double amount) {
        Double exchangeRate = exchangeService.getExchangeRate(base, symbol);
        Double convertedAmount = exchangeRate * amount;

        Conversion conversion = new Conversion(base, symbol, exchangeRate, convertedAmount);
        return conversionRepository.save(conversion);
    }

    public List<Conversion> getConversionHistory(LocalDate date) {
        return conversionRepository.findAllByDate(date);
    }

}
