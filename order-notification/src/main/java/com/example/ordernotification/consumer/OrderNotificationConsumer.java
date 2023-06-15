package com.example.ordernotification.consumer;

import com.example.orderdto.dto.OrderChangesPayload;
import com.example.ordernotification.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Component
public class OrderNotificationConsumer {

    private final ObjectMapper mapper;

    private final EmailService emailService;

    @KafkaListener(topics = "order.public.order_outbox", groupId = "order_group")
    public void listenPriceChangedMessage(ConsumerRecord<String, String> record) throws JsonProcessingException {
        var node = mapper.readTree(record.value());
        var event = node.path("payload").path("after");
        OrderChangesPayload payload = mapper.readValue(event.path("payload").asText(), OrderChangesPayload.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        var message = String.join("","New order accepted from ", payload.getClient()
                                                    , ", supplier is ", payload.getSupplier()
                                                    , "date is ", payload.getDate().format(formatter));
        emailService.sendSimpleEmail("Gav16071997@gmail.com", "New order", message);
    }

}
