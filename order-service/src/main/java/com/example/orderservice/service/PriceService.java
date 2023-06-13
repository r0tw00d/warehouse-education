package com.example.orderservice.service;

import com.example.pricedto.price.PriceType;

import java.math.BigDecimal;

public interface PriceService {
    public BigDecimal getPriceByProductIdAndPriceType(Long id, PriceType priceType);
}
