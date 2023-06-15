package com.example.warehouse.service;

import com.example.warehouse.domain.Leftover;
import com.example.warehouse.repository.LeftoverRepository;
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

    @Override
    public void create(Leftover leftover) {
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
}
