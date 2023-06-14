package com.example.warehouse.service;

import com.example.pricedto.price.PriceChangesPayload;
import com.example.warehouse.domain.PriceLifecycleEvent;
import com.example.warehouse.domain.PriceOutbox;
import com.example.warehouse.repository.PriceOutboxRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class PriceOutboxServiceJpa implements PriceOutboxService {

    private final PriceOutboxRepository repository;

    private final ObjectMapper mapper;

    @SneakyThrows
    @Override
    public void createPriceOutboxMessage(PriceChangesPayload priceChangesPayload) {
        String message = mapper.writeValueAsString(priceChangesPayload);
        PriceOutbox priceOutbox = PriceOutbox.builder()
                .key(UUID.randomUUID())
                .eventType(PriceLifecycleEvent.PRICE_CREATED)
                .payload(message)
                .build();
        repository.save(priceOutbox);
    }
}
