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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConversionControllerTest {

    @Mock
    private ConversionService conversionService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private ConversionController conversionController;

    @Test
    public void testConvertCurrency() {
        Conversion conversion = new Conversion();
        conversion.setId(1);
        conversion.setRate(1.0);
        conversion.setAmount(10.0);
        when(conversionService.convertCurrency("USD", "EUR", 10.0)).thenReturn(conversion);

        ConversionDTO conversionDTO = new ConversionDTO();
        conversionDTO.setId(1);
        conversionDTO.setRate(1.0);
        conversionDTO.setAmount(10.0);
        when(modelMapper.map(conversion, ConversionDTO.class)).thenReturn(conversionDTO);

        ResponseEntity<ConversionDTO> response = conversionController.convertCurrency("USD", "EUR", 10.0);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(conversionDTO, response.getBody());
    }

    @Test
    void getConversionHistoryTest() {
        LocalDate date = LocalDate.now();
        List<Conversion> conversions = new ArrayList<>();
        conversions.add(new Conversion("USD", "EUR", 0.85, 85.0));
        conversions.add(new Conversion("USD", "GBP", 0.75, 75.0));
        List<ConversionDTO> conversionDTOs = new ArrayList<>();
        conversionDTOs.add(new ConversionDTO());
        conversionDTOs.add(new ConversionDTO());

        when(conversionService.getConversionHistory(date)).thenReturn(conversions);
        when(modelMapper.map(any(), eq(ConversionDTO.class))).thenReturn(new ConversionDTO());

        Pageable pageable = mock(Pageable.class);
        when(pageable.getOffset()).thenReturn(0L);
        when(pageable.getPageSize()).thenReturn(10);

        ResponseEntity<Page<ConversionDTO>> response = conversionController.getConversionHistory(pageable, date);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(conversionDTOs.size(), response.getBody().getContent().size());
        verify(conversionService, times(1)).getConversionHistory(date);
        verify(modelMapper, times(conversions.size())).map(any(), eq(ConversionDTO.class));
    }


}
