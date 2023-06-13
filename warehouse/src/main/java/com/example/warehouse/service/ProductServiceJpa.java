package com.example.warehouse.service;

import com.example.warehouse.domain.Characteristic;
import com.example.warehouse.domain.Product;
import com.example.warehouse.dto.CharacteristicDto;
import com.example.warehouse.dto.CharacteristicResponse;
import com.example.warehouse.dto.ProductDto;
import com.example.warehouse.dto.ProductResponse;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.exception.ProductNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceJpa implements ProductService {

    private final ProductRepository repository;

    private final CharacteristicService characteristicService;

    @Transactional
    @Override
    public void create(ProductDto dto) {
        var product = Product.builder()
                .name(dto.getName())
                .barcode(dto.getBarcode())
                .build();
        var characteristics = convertDtoCharacteristicsToEntityList(dto, product);

        product.setCharacteristics(characteristics);
        repository.save(product);

    }

    private Set<Characteristic> convertDtoCharacteristicsToEntityList(ProductDto dto, Product product) {
        return dto.getCharacteristics()
                .stream()
                .map(e -> convertCharacteristicDtoToEntity(product, e))
                .collect(Collectors.toSet());
    }

    private Characteristic convertCharacteristicDtoToEntity(Product product, CharacteristicDto e) {
        Characteristic fromDb = characteristicService.fetchByNameAndValue(e.getName(), e.getValue());
        if (fromDb == null) {
            return Characteristic.builder()
                    .name(e.getName())
                    .value(e.getValue())
                    .products(new HashSet<>())
                    .build();
        } else {
            return fromDb;
        }

    }

    @Override
    public ProductResponse getById(Long id) {
        return repository.fetchById(id).orElseThrow(() -> new ProductNotFoundException("id", String.valueOf(id)));
    }

    @Override
    public ProductResponse getByName(String name) {
        return repository.fetchByName(name).orElseThrow(() -> new ProductNotFoundException("name", name));

    }

    @Override
    public ProductResponse getByBarcode(String barcode) {
        return repository.fetchByBarcode(barcode).orElseThrow(() -> new ProductNotFoundException("barcode", barcode));
    }

    @Transactional
    @Override
    public void updateById(Long id, Product product) {
        Product fromDb = findById(id);
        BeanUtils.copyProperties(product, fromDb, "id");
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public void addAllCharacteristics(Long id, List<CharacteristicDto> characteristics) {
        Product product = findById(id);
        characteristics.stream()
                .map(e -> convertCharacteristicDtoToEntity(product, e))
                .filter(e -> e.getProducts().contains(product) == false)
                .forEach(product::addCharacteristic);
    }

    @Transactional
    @Override
    public void deleteAllCharacteristics(Long id, List<CharacteristicDto> characteristics) {
        Product product = findById(id);
        characteristics.stream()
                .map(e -> characteristicService.fetchByNameAndValue(e.getName(), e.getValue()))
                .forEach(product::removeCharacteristic);
    }

    @Override
    public List<CharacteristicResponse> getAllCharacteristicById(Long id) {
        List<CharacteristicResponse> characteristics = characteristicService.getAllById(id);
        if (characteristics.isEmpty()) {
            throw new EntityNotFoundException("");
        }
        return characteristics;
    }


    private Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException("id", String.valueOf(id)));
    }
}
