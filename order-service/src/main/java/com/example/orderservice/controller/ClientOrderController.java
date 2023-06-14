package com.example.orderservice.controller;

import com.example.orderservice.domain.ClientOrder;
import com.example.orderservice.service.ClientOrderService;
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
public class ClientOrderController {

    private final ClientOrderService service;

    @ResponseStatus(OK)
    @GetMapping("order/id/{id}")
    public ClientOrder getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @ResponseStatus(CREATED)
    @PostMapping(value = "/id/{clientId}/order", consumes = APPLICATION_JSON_VALUE)
    public void create(@PathVariable Long clientId, @RequestBody ClientOrder clientOrder) {
        service.create(clientId, clientOrder);
    }
}
