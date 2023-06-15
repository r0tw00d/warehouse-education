package com.example.warehouse.service;

import com.example.warehouse.domain.Leftover;

import java.math.BigDecimal;

public interface LeftoverService {

    void create(Leftover leftover);

    Leftover getByProductId(Long productId);

    BigDecimal getValueByProductId(Long productId);

}
