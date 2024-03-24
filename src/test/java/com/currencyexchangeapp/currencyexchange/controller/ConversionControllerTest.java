package com.currencyexchangeapp.currencyexchange.controller;

import com.currencyexchangeapp.currencyexchange.controller.ConversionController;
import com.currencyexchangeapp.currencyexchange.dto.ConversionDTO;
import com.currencyexchangeapp.currencyexchange.model.Conversion;
import com.currencyexchangeapp.currencyexchange.repository.ConversionRepository;
import com.currencyexchangeapp.currencyexchange.service.ConversionService;
import com.currencyexchangeapp.currencyexchange.util.PaginationUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConversionControllerTest {

    @Mock
    private ConversionService conversionService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ConversionController conversionController;

    @Mock
    private ConversionRepository conversionRepository; // Add mock repository

    @Test
    public void testConvertCurrency() {
        // Mock conversion service response
        Conversion conversion = new Conversion();
        conversion.setId(1);
        conversion.setRate(1.0);
        conversion.setAmount(10.0);
        when(conversionService.convertCurrency("USD", "EUR", 10.0)).thenReturn(conversion);

        // Mock model mapper response
        ConversionDTO conversionDTO = new ConversionDTO();
        conversionDTO.setId(1);
        conversionDTO.setRate(1.0);
        conversionDTO.setAmount(10.0);
        when(modelMapper.map(conversion, ConversionDTO.class)).thenReturn(conversionDTO);

        // Call controller method
        ResponseEntity<ConversionDTO> response = conversionController.convertCurrency("USD", "EUR", 10.0);

        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(conversionDTO, response.getBody());
    }

    @Test
    public void testGetConversionHistory() {
        // Mock conversion repository response
        LocalDate date = LocalDate.now();
        Conversion conversion = new Conversion();
        conversion.setId(1);
        conversion.setRate(1.0);
        conversion.setAmount(10.0);
        List<Conversion> conversionList = Collections.singletonList(conversion);
        when(conversionRepository.findAllByDate(any(LocalDate.class))).thenReturn(conversionList);

        // Mock model mapper response
        ConversionDTO conversionDTO = new ConversionDTO();
        conversionDTO.setId(1);
        conversionDTO.setRate(1.0);
        conversionDTO.setAmount(10.0);
        when(modelMapper.map(conversion, ConversionDTO.class)).thenReturn(conversionDTO);

        // Mock pageable object
        Pageable pageable = PageRequest.of(0, 10); // Create a pageable object

        // Call controller method with valid pageable object
        Page<ConversionDTO> expectedPage = new PageImpl<>(Collections.singletonList(conversionDTO));
        when(PaginationUtil.paginateList(any(Pageable.class), any(List.class))).thenReturn(expectedPage);
        ResponseEntity<Page<ConversionDTO>> response = conversionController.getConversionHistory(pageable, date);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getContent().size());
    }
}
