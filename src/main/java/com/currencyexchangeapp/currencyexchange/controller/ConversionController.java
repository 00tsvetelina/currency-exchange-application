package com.currencyexchangeapp.currencyexchange.controller;

import com.currencyexchangeapp.currencyexchange.dto.ConversionDTO;
import com.currencyexchangeapp.currencyexchange.model.Conversion;
import com.currencyexchangeapp.currencyexchange.service.ConversionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ConversionController {

    private final ConversionService conversionService;
    private final ModelMapper modelMapper;

    // POST
    // add convertCurrency method
    @PostMapping("/currency")
    public ResponseEntity<ConversionDTO> convertCurrency (@RequestParam String base, String symbol, Double amount) {
        Conversion conversion = conversionService.convertCurrency(base, symbol, amount);
        ConversionDTO conversionDTO = modelMapper.map(conversion, ConversionDTO.class);


        return new ResponseEntity<>(conversionDTO, HttpStatus.CREATED);
    }

    // GET
    // add getConversionHistoryList method
}
