package com.example.orderservice.service;

import com.example.orderservice.domain.OrderItem;

import java.util.List;

public interface OrderItemService {

    void create(OrderItem characteristic);

    List<OrderItem> getAllById(long id);

    void deleteAllById(Long id);

}
