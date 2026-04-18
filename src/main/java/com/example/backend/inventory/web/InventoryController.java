package com.example.backend.inventory.web;

import com.example.backend.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Inventory Management", description = "Endpoints for stock management and availability checks")
@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @Operation(
        summary = "Add stock to an item",
        description = """
            Increases the available inventory count for a specific catalogue item.
            
            ### Parameters
            - **catalogueItemId**: ID of the item in the catalogue.
            - **quantity**: Amount of stock to add (e.g., `100`).
            """
    )
    @PostMapping("/{catalogueItemId}/stock")
    public void addStock(@PathVariable Integer catalogueItemId, @RequestParam Integer quantity) {
        inventoryService.addStock(catalogueItemId, quantity);
    }
    
    @Operation(
        summary = "Check item availability",
        description = """
            Verifies if there is sufficient stock available for a required quantity.
            
            ### Returns
            `true` if the required quantity is available, `false` otherwise.
            """
    )
    @GetMapping("/{catalogueItemId}/availability")
    public boolean checkAvailability(@PathVariable Integer catalogueItemId, @RequestParam Integer quantity) {
        return inventoryService.checkAvailability(catalogueItemId, quantity);
    }
}
