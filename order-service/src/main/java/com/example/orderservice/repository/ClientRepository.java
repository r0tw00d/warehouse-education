package com.example.orderservice.repository;

import com.example.orderservice.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long>  {
    @Query("select c " +
            "from Client as c " +
            "where c.name = :name"
    )
    Optional<Client> fetchByName(String name);

    @Query("select c " +
            "from Client as c " +
            "where c.inn = :inn")
    Optional<Client> fetchByInn(String inn);
}
