package com.example.warehouse.repository;

import com.example.warehouse.domain.Characteristic;
import com.example.warehouse.domain.Leftover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface LeftoverRepository extends JpaRepository<Leftover, Long> {

    @Query("select l.value " +
            "from Leftover l " +
            "where l.product.id = :id")
    BigDecimal getValueByProductId(Long id);

    @Query("select l " +
            "from Leftover l " +
            "where l.product.id = :id")
    Leftover getByProductId(Long id);

}
