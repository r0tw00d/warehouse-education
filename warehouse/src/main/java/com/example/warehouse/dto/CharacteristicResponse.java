package com.example.warehouse.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface CharacteristicResponse {
    Long getId();
    String getName();
    String getValue();
}
