package com.currencyexchangeapp.currencyexchange.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConversionDTO {
    private Integer id;
    private Double rate;

    private Double amount;
}
