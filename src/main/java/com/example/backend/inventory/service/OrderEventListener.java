package com.example.backend.inventory.service;

import com.example.backend.order.domain.dto.OrderItemDto;
import com.example.backend.order.domain.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private final InventoryService inventoryService;

    @ApplicationModuleListener
    public void on(OrderPlacedEvent event) {
        for (OrderItemDto item : event.items()) {
            inventoryService.decrementStock(item.catalogueItemId(), item.quantity());
        }
    }
}
