package com.example.warehouse.controller;

import com.example.warehouse.domain.Leftover;
import com.example.warehouse.domain.Price;
import com.example.warehouse.dto.LeftoverDto;
import com.example.warehouse.dto.ProductResponse;
import com.example.warehouse.service.LeftoverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class LeftoverController {

    private final LeftoverService service;

    @ResponseStatus(CREATED)
    @PostMapping(value = "/leftover", consumes = APPLICATION_JSON_VALUE)
    public void create(@RequestBody LeftoverDto leftoverDto) {
        service.create(leftoverDto);
    }

    @ResponseStatus(OK)
    @GetMapping("/id/{id}/leftovervalue")
    public BigDecimal getById(@PathVariable Long id) {return service.getValueByProductId(id);}
}
