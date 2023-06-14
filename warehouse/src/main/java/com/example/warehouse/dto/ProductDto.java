package com.example.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String name;
    private String barcode;
    private Set<CharacteristicDto> characteristics;

}
