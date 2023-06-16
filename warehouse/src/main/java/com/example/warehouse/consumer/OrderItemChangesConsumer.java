package com.example.warehouse.consumer;

import com.example.orderdto.dto.OrderItemChangesPayload;
import com.example.pricedto.price.PriceChangesPayload;
import com.example.warehouse.service.LeftoverService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderItemChangesConsumer {
    private final ObjectMapper mapper;

    public final LeftoverService service;

    @KafkaListener(topics = "order.public.order_item_outbox", groupId = "order_group")
    public void listenPriceChangedMessage(ConsumerRecord<String, String> record) throws JsonProcessingException {
        var node = mapper.readTree(record.value());
        var event = node.path("payload").path("after");
        OrderItemChangesPayload payload = mapper.readValue(event.path("payload").asText(), OrderItemChangesPayload.class);
        service.updateLeftover(payload);
    }
}
