package com.example.orderservice.service;

import com.example.pricedto.price.PriceType;

import java.math.BigDecimal;

public interface WarehouseService {
    public BigDecimal getPriceByProductIdAndPriceType(Long id, PriceType priceType);

    public BigDecimal getLeftoverByProductId(Long productId);
}
