package com.example.orderservice.repository;

import com.example.orderservice.domain.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long>  {

    @Query("select c " +
            "from ClientOrder c " +
            "where c.client.id = :id")
    List<ClientOrder> fetchAllByClientId(Long id);

    @Query("select c " +
            "from ClientOrder c " +
            "where c.client.name = :name")
    List<ClientOrder> fetchAllByClientName(String name);

    @Query("select c " +
            "from ClientOrder c " +
            "where c.client.inn = :inn")
    List<ClientOrder> fetchAllByClientInn(String inn);

}
