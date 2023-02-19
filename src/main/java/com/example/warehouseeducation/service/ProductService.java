package com.example.warehouseeducation.service;

import com.example.warehouseeducation.domain.Product;
import com.example.warehouseeducation.dto.ProductResponse;

public interface ProductService {

    void create(Product product);

    ProductResponse getById(Long id);

    ProductResponse getByName(String name);

    ProductResponse getByBarcode(String barcode);

    void updateById(Long id, Product product);

    void deleteById(Long id);
}
