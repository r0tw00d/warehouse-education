package com.example.warehouse.service;

import com.example.pricedto.price.PriceChangesPayload;

public interface PriceOutboxService {

    void createPriceOutboxMessage(PriceChangesPayload priceChangesPayload);

}
