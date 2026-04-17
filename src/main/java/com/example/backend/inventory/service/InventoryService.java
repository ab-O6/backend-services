package com.example.backend.inventory.service;

import com.example.backend.inventory.api.InventoryApi;
import com.example.backend.inventory.domain.entity.InventoryLevel;
import com.example.backend.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService implements InventoryApi {

    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean checkAvailability(Integer catalogueItemId, Integer requiredQuantity) {
        return inventoryRepository.findByCatalogueItemId(catalogueItemId)
                .map(level -> level.getQuantity() >= requiredQuantity)
                .orElse(false);
    }
    
    @Transactional
    public void addStock(Integer catalogueItemId, Integer quantity) {
        InventoryLevel level = inventoryRepository.findByCatalogueItemId(catalogueItemId)
                .orElseGet(() -> new InventoryLevel(catalogueItemId, 0));
        level.setQuantity(level.getQuantity() + quantity);
        inventoryRepository.save(level);
    }

    @Transactional
    public void decrementStock(Integer catalogueItemId, Integer quantity) {
        InventoryLevel level = inventoryRepository.findByCatalogueItemId(catalogueItemId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory item not found"));
        if (level.getQuantity() < quantity) {
            throw new IllegalStateException("Insufficient stock for item: " + catalogueItemId);
        }
        level.setQuantity(level.getQuantity() - quantity);
        inventoryRepository.save(level);
    }
}
