package com.example.warehouse.repository;

import com.example.warehouse.domain.Characteristic;
import com.example.warehouse.dto.CharacteristicResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {

    @Query("select c " +
            "from Characteristic c " +
            "left join fetch c.products p " +
            "where p.id = :id")
    List<CharacteristicResponse> fetchAllById(Long id);

    @Query("select c " +
            "from Characteristic c " +
            "where c.name = :name and c.value = :value")
    Characteristic fetchByNameAndValue(String name, String value);

}
