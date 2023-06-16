package com.example.orderservice.repository;

import com.example.orderservice.domain.OrderItemOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemOutboxRepository extends JpaRepository<OrderItemOutbox, Long> {
}
