package com.example.orderservice.controller;

import com.example.orderservice.domain.Order;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api/v1/client/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @ResponseStatus(OK)
    @GetMapping("order/id/{id}")
    public Order getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @ResponseStatus(CREATED)
    @PostMapping(value = "/id/{clientId}/order", consumes = APPLICATION_JSON_VALUE)
    public void create(@PathVariable Long clientId, @RequestBody Order order) {
        service.create(clientId, order);
    }
}
