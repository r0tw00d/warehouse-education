package com.example.warehouse.service;

import com.example.warehouse.domain.Leftover;
import com.example.warehouse.dto.LeftoverDto;

import java.math.BigDecimal;

public interface LeftoverService {

    void create(LeftoverDto leftoverDto);

    Leftover getByProductId(Long productId);

    BigDecimal getValueByProductId(Long productId);

}
