package com.example.pricehistory.service;

import com.example.pricedto.price.PriceChangesPayload;
import com.example.pricehistory.repository.PriceHistoryRepository;
import com.example.pricehistory.domain.PriceHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PriceHistoryServiceJpa implements PriceHistoryService {

    private final PriceHistoryRepository repository;
    @Override
    @Transactional
    public void save(PriceChangesPayload priceChangesPayload) {
         var priceHistory = PriceHistory.builder()
                 .priceType(priceChangesPayload.getPriceType())
                 .productId(priceChangesPayload.getProductId())
                 .newValue(priceChangesPayload.getNewValue())
                 .oldValue(priceChangesPayload.getOldValue())
                 .dateOfChange(priceChangesPayload.getDateOfChange())
                 .build();
         repository.save(priceHistory);
    }
}
