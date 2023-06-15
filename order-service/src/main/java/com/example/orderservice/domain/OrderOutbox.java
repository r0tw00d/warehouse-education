package com.example.orderservice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_outbox")
public class OrderOutbox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private UUID key;
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private OrderLifecycleEvent eventType;
    @Column(nullable = false)
    private String payload;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderOutbox that = (OrderOutbox) o;
        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
