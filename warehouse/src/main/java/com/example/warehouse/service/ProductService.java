package com.example.warehouse.service;

import com.example.warehouse.domain.Product;
import com.example.warehouse.dto.CharacteristicDto;
import com.example.warehouse.dto.CharacteristicResponse;
import com.example.warehouse.dto.ProductDto;
import com.example.warehouse.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    void create(ProductDto product);

    ProductResponse getById(Long id);

    ProductResponse getByName(String name);

    ProductResponse getByBarcode(String barcode);

    void updateById(Long id, Product product);

    void deleteById(Long id);

    void addAllCharacteristics(Long id, List<CharacteristicDto> characteristics);

    void deleteAllCharacteristics(Long id, List<CharacteristicDto> characteristics);

    List<CharacteristicResponse> getAllCharacteristicById(Long id);
}
