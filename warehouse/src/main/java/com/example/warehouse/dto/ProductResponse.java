package com.example.warehouse.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface ProductResponse {

    Long getId();

    String getName();

    String getBarcode();

    Set<CharacteristicResponse> getCharacteristics();
}
