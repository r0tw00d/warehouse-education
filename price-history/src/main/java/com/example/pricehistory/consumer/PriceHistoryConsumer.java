package com.example.pricehistory.consumer;

import com.example.pricedto.price.PriceChangesPayload;
import com.example.pricehistory.service.PriceHistoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PriceHistoryConsumer {

    private final ObjectMapper mapper;

    public final PriceHistoryService service;

    @KafkaListener(topics = "price.public.price_outbox", groupId = "price_group")
    public void listenPriceChangedMessage(ConsumerRecord<String, String> record) throws JsonProcessingException {
        var node = mapper.readTree(record.value());
        var event = node.path("payload").path("after");
        PriceChangesPayload payload = mapper.readValue(event.path("payload").asText(), PriceChangesPayload.class);
        service.save(payload);
    }

}
