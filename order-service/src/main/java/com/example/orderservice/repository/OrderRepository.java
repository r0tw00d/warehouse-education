package com.example.orderservice.repository;

import com.example.orderservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>  {

    @Query("select o " +
            "from Order o " +
            "where o.client.id = :id")
    List<Order> fetchAllByClientId(Long id);

    @Query("select o " +
            "from Order o " +
            "where o.client.name = :name")
    List<Order> fetchAllByClientName(String name);

    @Query("select o " +
            "from Order o " +
            "where o.client.inn = :inn")
    List<Order> fetchAllByClientInn(String inn);

}
