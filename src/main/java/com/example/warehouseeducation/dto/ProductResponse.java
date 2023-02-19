package com.example.warehouseeducation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface ProductResponse {

    Long getId();

    String getName();

    String getBarcode();

}
