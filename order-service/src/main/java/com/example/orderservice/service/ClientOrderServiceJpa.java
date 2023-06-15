package com.example.orderservice.service;

import com.example.orderdto.dto.OrderChangesPayload;
import com.example.orderservice.domain.ClientOrder;
import com.example.orderservice.domain.OrderItem;
import com.example.orderservice.repository.ClientOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClientOrderServiceJpa implements ClientOrderService {

    private final ClientOrderRepository repository;

    private final OrderItemService orderItemService;

    private final PriceService priceService;

    private final ClientService clientService;

    private final OrderOutboxService orderOutboxService;

    @Transactional
    @Override
    public void create(Long clientId, ClientOrder clientOrder) {
        clientOrder.setClient(clientService.findById(clientId));
        clientOrder.setOrderItems(setPriceForOrderItems(clientOrder));
        var orderChangesPayload = OrderChangesPayload.builder()
                        .client(clientOrder.getClient().getName())
                        .supplier(clientOrder.getSupplier())
                        .date(clientOrder.getDate())
                        .build();
        repository.save(clientOrder);
        orderOutboxService.createPriceOutboxMessage(orderChangesPayload);
    }

    private List<OrderItem> setPriceForOrderItems(ClientOrder clientOrder) {
        var orderItems = clientOrder.getOrderItems();
        orderItems.stream()
                .forEach(e -> e.setPrice(priceService.getPriceByProductIdAndPriceType(e.getProductId(), clientOrder.getClient().getPriceType())));
        return orderItems;
    }

    @Override
    public ClientOrder findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Transactional
    @Override
    public void updateById(Long id, ClientOrder clientOrder) {
        var fromDb = findById(id);
        BeanUtils.copyProperties(clientOrder, fromDb, "id");
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        var fromDb = findById(id);
        repository.delete(fromDb);
    }

    @Override
    public List<OrderItem> getAllOrderItemById(Long id) {
        return orderItemService.getAllById(id);
    }

    @Transactional
    @Override
    public void addAllOrderItems(Long id, List<OrderItem> orderItems) {
        var order = findById(id);
        orderItems.stream()
                .filter(e -> e.getId() == null)
                .forEach(e -> e.setPrice(priceService.getPriceByProductIdAndPriceType(e.getProductId(), order.getClient().getPriceType())));
        orderItems.forEach(order::addOrderItem);
    }

    @Transactional
    @Override
    public void deleteAllOrderItems(Long id, List<OrderItem> orderItems) {
        var order = findById(id);
        orderItems.forEach(order::removeOrderItem);
    }
}
