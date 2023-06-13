package com.example.orderservice.service;

import com.example.pricedto.price.PriceDto;
import com.example.pricedto.price.PriceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.math.BigDecimal;
import java.time.Duration;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final WebClient webClient;

    @Override
    public BigDecimal getPriceByProductIdAndPriceType(Long id, PriceType priceType) {
        var priceDto = getPriceDtoByProductIdAndPriceType(id, priceType);
        return priceDto.getValue();
    }

    private PriceDto getPriceDtoByProductIdAndPriceType(Long id, PriceType priceType){
        return webClient
                .get()
                .uri(String.join("", "http://localhost:8085/api/v1/product/id/", id.toString(), "/price/", priceType.toString()))
                .retrieve()
                .bodyToMono(PriceDto.class)
                .retryWhen(Retry.fixedDelay(3, Duration.ofMillis(100)))
                .block();
    }
}
