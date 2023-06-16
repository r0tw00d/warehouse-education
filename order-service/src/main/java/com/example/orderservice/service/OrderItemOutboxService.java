package com.example.orderservice.service;

import com.example.orderdto.dto.OrderItemChangesPayload;

public interface OrderItemOutboxService {

    void createOrderItemOutboxMessage(OrderItemChangesPayload orderItemChangesPayload);

}
