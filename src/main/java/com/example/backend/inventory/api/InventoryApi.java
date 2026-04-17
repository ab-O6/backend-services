package com.example.backend.inventory.api;

public interface InventoryApi {
    boolean checkAvailability(Integer catalogueItemId, Integer requiredQuantity);
}
