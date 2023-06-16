package com.example.orderservice.service;

import com.example.orderdto.dto.OrderItemChangesPayload;
import com.example.orderservice.domain.OrderItemOutbox;
import com.example.orderservice.repository.OrderItemOutboxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.example.orderservice.domain.OrderLifecycleEvent.ORDER_CREATED;

@Service
@AllArgsConstructor
@Transactional
public class OrderItemOutboxServiceJpa implements OrderItemOutboxService {

    private final OrderItemOutboxRepository repository;

    private final ObjectMapper mapper;

    @SneakyThrows(value = JsonProcessingException.class)
    @Override
    public void createOrderItemOutboxMessage(OrderItemChangesPayload orderItemChangesPayload) {
        var orderItemOutbox = OrderItemOutbox.builder()
                .key(UUID.randomUUID())
                .eventType(ORDER_CREATED)
                .payload(mapper.writeValueAsString(orderItemChangesPayload))
                .build();
        repository.save(orderItemOutbox);
    }
}
