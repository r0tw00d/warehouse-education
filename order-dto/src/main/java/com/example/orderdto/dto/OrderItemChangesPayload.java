package com.example.orderdto.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemChangesPayload {

    private Long productId;

    private BigDecimal quantity;
}
