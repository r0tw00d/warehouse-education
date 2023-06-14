package com.example.orderservice.service;

import com.example.orderservice.domain.OrderItem;
import com.example.orderservice.repository.OrderItemRepository;
import com.example.pricedto.price.PriceType;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderItemServiceJpa implements OrderItemService {

    private final OrderItemRepository repository;

    @Transactional
    @Override
    public void create(OrderItem orderItem) {
        repository.save(orderItem);
    }

    @Override
    public List<OrderItem> getAllById(long id) {
        var orderItems = repository.fetchAllByOrderId(id);
        if (orderItems.isEmpty()) {
            throw new EntityNotFoundException("");
        }
        return orderItems;
    }

    @Transactional
    @Override
    public void deleteAllById(Long id) {
        var fromDb = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        repository.delete(fromDb);
    }


}
