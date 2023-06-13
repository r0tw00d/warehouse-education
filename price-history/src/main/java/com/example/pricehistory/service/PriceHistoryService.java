package com.example.pricehistory.service;

import com.example.pricedto.price.PriceChangesPayload;

public interface PriceHistoryService {
    void save(PriceChangesPayload priceChangesPayload);
}
