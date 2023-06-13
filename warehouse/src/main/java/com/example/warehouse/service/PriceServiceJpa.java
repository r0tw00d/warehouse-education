package com.example.warehouse.service;

import com.example.pricedto.price.PriceChangesPayload;
import com.example.pricedto.price.PriceType;
import com.example.warehouse.dto.PriceResponce;
import com.example.warehouse.exception.PriceNotFoundException;
import com.example.warehouse.repository.PriceRepository;
import com.example.warehouse.domain.Price;
import com.example.warehouse.exception.ProductNotFoundException;
import com.example.warehouse.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PriceServiceJpa implements PriceService {

    private final PriceRepository repository;

    private final ProductRepository productRepository;

    private final PriceOutboxService priceService;

    @Transactional
    @Override
    public void createPrices(Long productId, List<Price> prices) {
        prices.forEach(price -> price.setProduct(productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("id", String.valueOf(productId)))));
        prices.forEach(this::create);
    }

    public void create(Price price) {
        Long id = price.getId();
        if (Objects.isNull(id)) {
            repository.save(price);
        } else {
            updatePrice(price);
        }
    }

    private void updatePrice(Price e) {
        Price fromDb = findById(e.getId());
        PriceChangesPayload priceChangesPayload = PriceChangesPayload.builder()
                .priceType(e.getPriceType())
                .productId(e.getProduct().getId())
                .dateOfChange(LocalDateTime.now())
                .oldValue(fromDb.getValue())
                .newValue(e.getValue())
                .build();
        BeanUtils.copyProperties(e, fromDb, "id");
        priceService.createPriceOutboxMessage(priceChangesPayload);

    }

    @Override
    public PriceResponce getById(Long id) {
        return repository.fetchById(id)
                .orElseThrow(() -> new PriceNotFoundException("id", String.valueOf(id)));
    }

    @Override
    public List<PriceResponce> getAllByProductId(Long id) {
        return repository.fetchAllByProductId(id);
    }

    @Override
    public PriceResponce getByProductIdAndPriceType(Long id, PriceType priceType) {
        return repository.fetchByProductIdAndPriceType(id, priceType)
                .orElseThrow(() -> new PriceNotFoundException("product_id and priceType", String.valueOf(id)+" and "+String.valueOf(priceType)));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Price price = findById(id);
        repository.delete(price);
    }

    @Transactional
    @Override
    public void deleteAllByProductId(Long id) {
        repository.deleteAll(repository.findAllByProductId(id));
    }

    private Price findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PriceNotFoundException("id", String.valueOf(id)));
    }
}
