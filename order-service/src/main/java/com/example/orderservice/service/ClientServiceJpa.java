package com.example.orderservice.service;

import com.example.orderservice.domain.Client;
import com.example.orderservice.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClientServiceJpa implements ClientService {

    private final ClientRepository repository;

    @Transactional
    @Override
    public void create(Client client) {
        repository.save(client);
    }

    @Override
    public Client findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Client findByName(String name) {
        return repository.fetchByName(name)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Client findByInn(String inn) {
        return repository.fetchByInn(inn)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Transactional
    @Override
    public void updateById(Long id, Client client) {
        var fromDb = findById(id);
        BeanUtils.copyProperties(client, fromDb, "id");
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        var fromDb = findById(id);
        repository.delete(fromDb);
    }
}
