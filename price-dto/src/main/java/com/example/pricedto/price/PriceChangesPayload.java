package com.example.pricedto.price;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class PriceChangesPayload {

        private Long productId;

        private BigDecimal oldValue;

        private BigDecimal newValue;

        private PriceType priceType;

        private LocalDateTime dateOfChange;

        public PriceChangesPayload(Long productId, BigDecimal oldValue, BigDecimal newValue, PriceType priceType) {
            this.productId = productId;
            this.oldValue = oldValue;
            this.newValue = newValue;
            this.priceType = priceType;
            this.dateOfChange = LocalDateTime.now();
        }
    }
