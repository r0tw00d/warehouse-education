package com.example.warehouse.controller;

import com.example.pricedto.price.PriceType;
import com.example.warehouse.domain.Price;
import com.example.warehouse.dto.PriceResponce;
import com.example.warehouse.service.PriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService service;

    @ResponseStatus(OK)
    @GetMapping(value = "/price/id/{id}", produces = APPLICATION_JSON_VALUE)
    public PriceResponce getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @ResponseStatus(OK)
    @GetMapping(value = "/id/{id}/price", produces = APPLICATION_JSON_VALUE)
    public List<PriceResponce> getAllByProductId(@PathVariable Long id) {
        return service.getAllByProductId(id);
    }

    @ResponseStatus(CREATED)
    @PostMapping(value = "/id/{id}/price", consumes = APPLICATION_JSON_VALUE)
    public void create(@PathVariable Long id, @RequestBody List<Price> prices) {
        service.createPrices(id, prices);
    }

    @ResponseStatus(OK)
    @GetMapping(value = "/id/{id}/price/{priceType}", produces = APPLICATION_JSON_VALUE)
    public PriceResponce getByProductIdAndPriceType(@PathVariable Long id, @PathVariable PriceType priceType) {
        return service.getByProductIdAndPriceType(id, priceType);
    }

    @ResponseStatus(OK)
    @GetMapping(value = "/id/{id}/price/test/{priceType}", produces = APPLICATION_JSON_VALUE)
    public PriceResponce getByIdAndPriceType(@PathVariable Long id, @PathVariable PriceType priceType) {
        return service.getByProductIdAndPriceType(id, priceType);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/price/id/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/id/{id}/price")
    public void deleteAllByProductId(@PathVariable Long id) {
        service.deleteAllByProductId(id);
    }
}
