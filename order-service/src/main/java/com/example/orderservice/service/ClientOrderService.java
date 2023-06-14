package com.example.orderservice.service;

import com.example.orderservice.domain.ClientOrder;
import com.example.orderservice.domain.OrderItem;

import java.util.List;

public interface ClientOrderService {

    void create(Long clientId, ClientOrder order);

    ClientOrder findById(Long id);

    void updateById(Long id, ClientOrder order);

    void deleteById(Long id);

    List<OrderItem> getAllOrderItemById(Long id);

    void addAllOrderItems(Long id, List<OrderItem> orderItems);

    void deleteAllOrderItems(Long id, List<OrderItem> orderItems);
}
