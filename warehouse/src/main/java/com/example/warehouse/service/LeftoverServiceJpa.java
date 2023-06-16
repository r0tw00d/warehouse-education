package com.example.warehouse.service;

import com.example.warehouse.domain.Leftover;
import com.example.warehouse.domain.Product;
import com.example.warehouse.dto.LeftoverDto;
import com.example.warehouse.exception.ProductNotFoundException;
import com.example.warehouse.repository.LeftoverRepository;
import com.example.warehouse.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LeftoverServiceJpa implements LeftoverService {

    private final LeftoverRepository repository;

    private final ProductRepository productRepository;

    @Transactional
    @Override
    public void create(LeftoverDto leftoverDto) {
        var leftover = Leftover.builder()
                        .product(findProductByProductId(leftoverDto.getProductId()))
                        .value(leftoverDto.getValue())
                        .build();
        repository.save(leftover);
    }

    @Override
    public Leftover getByProductId(Long productId) {
        return repository.getByProductId(productId);
    }

    @Override
    public BigDecimal getValueByProductId(Long productId) {
        return repository.getValueByProductId(productId);
    }

    private Product findProductByProductId(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("id", String.valueOf(productId)));
    }
}
