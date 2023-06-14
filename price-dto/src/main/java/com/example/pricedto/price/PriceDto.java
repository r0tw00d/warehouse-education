package com.example.pricedto.price;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PriceDto {
    private PriceType priceType;
    private BigDecimal value;
}
