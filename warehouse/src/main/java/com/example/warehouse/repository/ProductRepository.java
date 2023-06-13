package com.example.warehouse.repository;

import com.example.warehouse.domain.Product;
import com.example.warehouse.dto.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository <Product, Long> {

    @Query("select p " +
            "from Product as p " +
            "left join fetch p.characteristics c " +
            "where p.id = :id")
    Optional<ProductResponse> fetchById(Long id);

    @Query("select p " +
            "from Product as p " +
            "left join fetch p.characteristics c " +
            "where p.name = :name")
    Optional<ProductResponse> fetchByName(String name);

    @Query("select p " +
            "from Product as p " +
            "left join fetch p.characteristics c " +
            "where p.barcode = :barcode")
    Optional<ProductResponse> fetchByBarcode(String barcode);

}
