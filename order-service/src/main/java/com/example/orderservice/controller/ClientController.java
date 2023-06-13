package com.example.orderservice.controller;

import com.example.orderservice.domain.Client;
import com.example.orderservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @ResponseStatus(OK)
    @GetMapping(value = "/id/{id}", produces = APPLICATION_JSON_VALUE)
    public Client getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @ResponseStatus(CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void create(@RequestBody Client client) {
        service.create(client);
    }
}
