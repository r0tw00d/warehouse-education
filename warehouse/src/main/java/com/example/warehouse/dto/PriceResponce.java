package com.example.warehouse.dto;

import com.example.pricedto.price.PriceType;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface PriceResponce {
    PriceType getPriceType();

    BigDecimal getValue();
}
