package com.example.warehouseeducation.service;

import com.example.warehouseeducation.domain.Product;
import com.example.warehouseeducation.dto.ProductResponse;
import com.example.warehouseeducation.repositury.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceJpa implements ProductService {

    private final ProductRepository repository;

    @Transactional
    @Override
    public void create(Product product) {
        repository.save(product);
    }

    @Override
    public ProductResponse getById(Long id) {
        return repository.fetchById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public ProductResponse getByName(String name) {
        return repository.fetchByName(name).orElseThrow(() -> new EntityNotFoundException());

    }

    @Override
    public ProductResponse getByBarcode(String barcode) {
        return repository.fetchByBarcode(barcode).orElseThrow(() -> new EntityNotFoundException());
    }

    @Transactional
    @Override
    public void updateById(Long id, Product product) {
        Product fromDb = findById(id);
        BeanUtils.copyProperties(product, fromDb, "id");
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }
}
