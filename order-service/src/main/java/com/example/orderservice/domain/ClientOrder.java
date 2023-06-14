package com.example.orderservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "client_order")
public class ClientOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "supplier", nullable = false)
    private String supplier;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "date", nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @Version
    private Integer version;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clientOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) {
        orderItem.setClientOrder(this);
        this.orderItems.add(orderItem);
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItem.setClientOrder(null);
        this.orderItems.remove(orderItem);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientOrder order = (ClientOrder) o;

        if (supplier != null ? !supplier.equals(order.supplier) : order.supplier != null) return false;
        if (client != null ? !client.equals(order.client) : order.client != null) return false;
        return date != null ? date.equals(order.date) : order.date == null;
    }

    @Override
    public int hashCode() {
        int result = supplier != null ? supplier.hashCode() : 0;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
