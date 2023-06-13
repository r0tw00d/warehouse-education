package com.example.warehouse.repository;

import com.example.warehouse.domain.PriceOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceOutboxRepository extends JpaRepository<PriceOutbox, Long> {
}
