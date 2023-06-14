package com.example.orderservice.service;

import com.example.orderservice.domain.Order;
import com.example.orderservice.domain.OrderItem;
import com.example.orderservice.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceJpa implements OrderService {

    private final OrderRepository repository;

    private final OrderItemService orderItemService;

    private final PriceService priceService;

    private final ClientService clientService;

    @Transactional
    @Override
    public void create(Long clientId, Order order) {
        order.setClient(clientService.findById(clientId));
        order.setOrderItems(setPriceForOrderItems(order));
        repository.save(order);
    }

    private List<OrderItem> setPriceForOrderItems(Order order) {
        var orderItems = order.getOrderItems();
        orderItems.stream()
                .forEach(e -> e.setPrice(priceService.getPriceByProductIdAndPriceType(e.getProductId(), order.getClient().getPriceType())));
        return orderItems;
    }

    @Override
    public Order findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Transactional
    @Override
    public void updateById(Long id, Order order) {
        var fromDb = findById(id);
        BeanUtils.copyProperties(order, fromDb, "id");
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        var fromDb = findById(id);
        repository.delete(fromDb);
    }

    @Override
    public List<OrderItem> getAllOrderItemById(Long id) {
        return orderItemService.getAllById(id);
    }

    @Transactional
    @Override
    public void addAllOrderItems(Long id, List<OrderItem> orderItems) {
        var order = findById(id);
        orderItems.stream()
                .filter(e -> e.getId() == null)
                .forEach(e -> e.setPrice(priceService.getPriceByProductIdAndPriceType(e.getProductId(), order.getClient().getPriceType())));
        orderItems.forEach(order::addOrderItem);
    }

    @Transactional
    @Override
    public void deleteAllOrderItems(Long id, List<OrderItem> orderItems) {
        var order = findById(id);
        orderItems.forEach(order::removeOrderItem);
    }
}
