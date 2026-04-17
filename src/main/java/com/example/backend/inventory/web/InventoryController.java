package com.example.backend.inventory.web;

import com.example.backend.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/{catalogueItemId}/stock")
    public void addStock(@PathVariable Integer catalogueItemId, @RequestParam Integer quantity) {
        inventoryService.addStock(catalogueItemId, quantity);
    }
    
    @GetMapping("/{catalogueItemId}/availability")
    public boolean checkAvailability(@PathVariable Integer catalogueItemId, @RequestParam Integer quantity) {
        return inventoryService.checkAvailability(catalogueItemId, quantity);
    }
}
