package com.example.warehouse.service;

import com.example.pricedto.price.PriceType;
import com.example.warehouse.dto.PriceResponce;
import com.example.warehouse.domain.Price;

import java.util.List;

public interface PriceService {
    void createPrices(Long productId, List<Price> prices);

    PriceResponce getById(Long id);

    List<PriceResponce> getAllByProductId(Long id);

    PriceResponce getByProductIdAndPriceType(Long id, PriceType priceType);

    void deleteById(Long id);

    void deleteAllByProductId(Long id);
}
