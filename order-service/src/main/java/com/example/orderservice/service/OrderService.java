package com.example.orderservice.service;

import com.example.orderservice.domain.Order;
import com.example.orderservice.domain.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    void create(Long clientId, Order order);

    Order findById(Long id);

    void updateById(Long id, Order order);

    void deleteById(Long id);

    List<OrderItem> getAllOrderItemById(Long id);

    void addAllOrderItems(Long id, List<OrderItem> orderItems);

    void deleteAllOrderItems(Long id, List<OrderItem> orderItems);
}
