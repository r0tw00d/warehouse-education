package com.example.warehouse.repository;

import com.example.pricedto.price.PriceType;
import com.example.warehouse.dto.PriceResponce;
import com.example.warehouse.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("select p " +
            "from Price p " +
            "where p.id = :id"
    )
    Optional<PriceResponce> fetchById(Long id);

    @Query("select p " +
            "from Price p " +
            "where p.product.id = :id"
    )
    List<PriceResponce> fetchAllByProductId(Long id);

    @Query("select p " +
            "from Price p " +
            "where p.product.id = :id " +
            "and p.priceType = :priceType"
    )
    Optional<PriceResponce> fetchByProductIdAndPriceType(Long id, PriceType priceType);

    @Modifying
    @Query("delete " +
            "from Price p " +
            "where p.product.id = :id"
    )
    void deleteAllByProductId(Long id);

    List<Price> findAllByProductId(Long productId);
}
