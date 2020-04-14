package com.currencyexchange.backend.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Currency {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id;

    private String FullCurrencyName;
    private String ShortCurrencyName;
    private float ConversionFactor;

}
