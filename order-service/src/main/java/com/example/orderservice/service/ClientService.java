package com.example.orderservice.service;

import com.example.orderservice.domain.Client;

public interface ClientService {

    void create(Client client);

    Client findById(Long id);

    Client findByName(String name);

    Client findByInn(String inn);

    void updateById(Long id, Client client);

    void deleteById(Long id);
}
