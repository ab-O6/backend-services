package com.example.backend.order.service;

import com.example.backend.order.api.OrderApi;
import com.example.backend.order.domain.dto.OrderItemDto;
import com.example.backend.order.domain.dto.PlaceOrderRequest;
import com.example.backend.order.domain.entity.Order;
import com.example.backend.order.domain.entity.OrderItem;
import com.example.backend.order.domain.event.OrderPlacedEvent;
import com.example.backend.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderApi {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public Integer placeOrder(PlaceOrderRequest request) {
        Order order = new Order(request.customerId());
        
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItemDto dto : request.items()) {
            OrderItem item = new OrderItem(dto.catalogueItemId(), dto.quantity(), dto.price());
            order.addItem(item);
            total = total.add(dto.price().multiply(BigDecimal.valueOf(dto.quantity())));
        }
        order.setTotalAmount(total);
        
        Order savedOrder = orderRepository.save(order);
        
        eventPublisher.publishEvent(new OrderPlacedEvent(savedOrder.getId(), request.items()));
        
        return savedOrder.getId();
    }
}
