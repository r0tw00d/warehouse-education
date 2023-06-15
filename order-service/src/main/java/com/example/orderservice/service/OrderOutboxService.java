package com.example.orderservice.service;

import com.example.orderdto.dto.OrderChangesPayload;

public interface OrderOutboxService {

    void createPriceOutboxMessage(OrderChangesPayload orderChangesPayload);

}
