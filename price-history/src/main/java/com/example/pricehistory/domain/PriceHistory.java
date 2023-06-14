package com.example.pricehistory.domain;

import com.example.pricedto.price.PriceType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "price_history")
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "price_type", nullable = false)
    private PriceType priceType;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "old_value", nullable = false)
    private BigDecimal newValue;

    @Column(name = "new_value", nullable = false)
    private BigDecimal oldValue;

    @Column(name = "date_of_change", nullable = false)
    private LocalDateTime dateOfChange;

    @Version
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceHistory that = (PriceHistory) o;

        if (priceType != that.priceType) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (newValue != null ? !newValue.equals(that.newValue) : that.newValue != null) return false;
        if (oldValue != null ? !oldValue.equals(that.oldValue) : that.oldValue != null) return false;
        if (dateOfChange != null ? !dateOfChange.equals(that.dateOfChange) : that.dateOfChange != null) return false;
        return version != null ? version.equals(that.version) : that.version == null;
    }

    @Override
    public int hashCode() {
        int result = priceType != null ? priceType.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (newValue != null ? newValue.hashCode() : 0);
        result = 31 * result + (oldValue != null ? oldValue.hashCode() : 0);
        result = 31 * result + (dateOfChange != null ? dateOfChange.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }
}
