package com.example.orderservice.repository;

import com.example.orderservice.domain.OrderOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderOutboxRepository extends JpaRepository<OrderOutbox, Long> {
}
