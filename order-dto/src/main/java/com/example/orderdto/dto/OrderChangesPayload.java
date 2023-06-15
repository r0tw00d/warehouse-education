package com.example.orderdto.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderChangesPayload {

    private String supplier;

    private String client;

    private LocalDateTime date;

}
