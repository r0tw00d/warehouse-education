package com.example.warehouse.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "price_outbox")
public class PriceOutbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private UUID key;
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private PriceLifecycleEvent eventType;
    @Column(nullable = false)
    private String payload;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceOutbox that = (PriceOutbox) o;
        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
