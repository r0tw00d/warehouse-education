package com.example.warehouse.service;

import com.example.warehouse.domain.Characteristic;
import com.example.warehouse.dto.CharacteristicResponse;
import com.example.warehouse.repository.CharacteristicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CharacteristicServiceJpa implements CharacteristicService {

    private final CharacteristicRepository repository;

    @Override
    public void create(Characteristic characteristic) {
        repository.save(characteristic);
    }

    @Override
    public List<CharacteristicResponse> getAllById(long id) {
        List<CharacteristicResponse> characteristics = repository.fetchAllById(id);
        if (characteristics.isEmpty()) {
            throw new EntityNotFoundException("");
        }
        return characteristics;
    }

    @Override
    public void deleteAllById(Long id) {

    }

    @Override
    public Characteristic fetchByNameAndValue(String name, String value) {
        return repository.fetchByNameAndValue(name, value);
    }
}
