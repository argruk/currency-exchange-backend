package com.currencyexchange.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyAddReceived {
    private String fullCurrencyName;
    private String shortCurrencyName;
    private float conversionFactor;
}
