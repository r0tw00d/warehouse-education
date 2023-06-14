package com.example.warehouse.service;

import com.example.warehouse.domain.Characteristic;
import com.example.warehouse.dto.CharacteristicResponse;

import java.util.List;

public interface CharacteristicService {

    void create(Characteristic characteristic);

    List<CharacteristicResponse> getAllById(long id);

    void deleteAllById(Long id);

    Characteristic fetchByNameAndValue(String name, String value);
}
