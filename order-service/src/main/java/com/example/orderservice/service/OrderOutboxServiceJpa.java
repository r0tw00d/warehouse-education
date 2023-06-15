package com.example.orderservice.service;

import com.example.orderdto.dto.OrderChangesPayload;
import com.example.orderservice.domain.OrderLifecycleEvent;
import com.example.orderservice.domain.OrderOutbox;
import com.example.orderservice.repository.OrderOutboxRepository;
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
public class OrderOutboxServiceJpa implements OrderOutboxService {

    private final OrderOutboxRepository repository;

    private final ObjectMapper mapper;

    @SneakyThrows(value = JsonProcessingException.class)
    @Override
    public void createPriceOutboxMessage(OrderChangesPayload orderChangesPayload) {
        var orderOutbox = OrderOutbox.builder()
                .key(UUID.randomUUID())
                .eventType(ORDER_CREATED)
                .payload(mapper.writeValueAsString(orderChangesPayload))
                .build();
        repository.save(orderOutbox);
    }
}
