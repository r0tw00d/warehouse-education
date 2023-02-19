package com.example.warehouseeducation.controller;

import com.example.warehouseeducation.domain.Product;
import com.example.warehouseeducation.dto.ProductResponse;
import com.example.warehouseeducation.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService service;

    @ResponseStatus(OK)
    @GetMapping("/id/{id}")
    public ProductResponse getById(@PathVariable Long id) {return service.getById(id);}

    @ResponseStatus(CREATED)
    @PostMapping(value = "/create", consumes = APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody Product product) {service.create(product);}

    @ResponseStatus(OK)
    @PutMapping(value = "/id/{id}", consumes = APPLICATION_JSON_VALUE)
    public void updateById(@PathVariable Long id, @RequestBody Product product) {service.updateById(id, product);}

    @ResponseStatus(OK)
    @DeleteMapping("id/{id}")
    public void deleteById(@PathVariable Long id) {service.deleteById(id);}
}
