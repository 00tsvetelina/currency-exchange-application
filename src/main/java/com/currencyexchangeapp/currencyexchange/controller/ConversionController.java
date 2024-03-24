package com.currencyexchangeapp.currencyexchange.controller;

import com.currencyexchangeapp.currencyexchange.dto.ConversionDTO;
import com.currencyexchangeapp.currencyexchange.model.Conversion;
import com.currencyexchangeapp.currencyexchange.service.ConversionService;
import com.currencyexchangeapp.currencyexchange.util.PaginationUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class ConversionController {

    private final ConversionService conversionService;
    private final ModelMapper modelMapper;

    @PostMapping("/currency")
    public ResponseEntity<ConversionDTO> convertCurrency (@RequestParam String base, String symbol, Double amount) {
        Conversion conversion = conversionService.convertCurrency(base, symbol, amount);
        ConversionDTO conversionDTO = modelMapper.map(conversion, ConversionDTO.class);


        return new ResponseEntity<>(conversionDTO, HttpStatus.CREATED);
    }

    @GetMapping("/conversionHistory")
    public ResponseEntity<Page<ConversionDTO>> getConversionHistory (Pageable pageable, @RequestParam LocalDate date) {
        List<ConversionDTO> conversionDTOList = conversionService.getConversionHistory(date)
                .stream()
                .map(conversion -> modelMapper.map(conversion, ConversionDTO.class))
                .toList();

        Page<ConversionDTO> dtoPage = PaginationUtil.paginateList(pageable, conversionDTOList);

        return ResponseEntity.ok(dtoPage);
    }
}
