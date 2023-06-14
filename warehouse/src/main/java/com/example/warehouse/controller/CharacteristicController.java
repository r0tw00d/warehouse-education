package com.example.warehouse.controller;

import com.example.warehouse.dto.CharacteristicDto;
import com.example.warehouse.dto.CharacteristicResponse;
import com.example.warehouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class CharacteristicController {

    public final ProductService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/id/{id}/characteristic", produces = APPLICATION_JSON_VALUE)
    public List<CharacteristicResponse> getAllById(@PathVariable Long id) {
        return service.getAllCharacteristicById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/id/{id}/characteristic", consumes = APPLICATION_JSON_VALUE)
    public void add(@PathVariable Long id, @RequestBody List<CharacteristicDto> characteristics){
        service.addAllCharacteristics(id, characteristics);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/id/{id}/characteristic")
    public void deleteById(@PathVariable Long id, @RequestBody List<CharacteristicDto> characteristics) {
        service.deleteAllCharacteristics(id, characteristics);
    }
}
