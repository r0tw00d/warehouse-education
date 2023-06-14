package com.example.orderservice.repository;

import com.example.orderservice.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("select o " +
            "from OrderItem o " +
            "where o.order.id = :id")
    List<OrderItem> fetchAllByOrderId(Long id);

}
