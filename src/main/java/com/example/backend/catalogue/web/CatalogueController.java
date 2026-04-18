package com.example.backend.catalogue.web;

import com.example.backend.catalogue.domain.dto.CatalogueItemDto;
import com.example.backend.catalogue.domain.dto.CreateCatalogueItemRequest;
import com.example.backend.catalogue.domain.dto.UpdateCatalogueItemRequest;
import com.example.backend.catalogue.service.CatalogueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Catalogue Management", description = "Endpoints for managing the product catalogue")
@RestController
@RequestMapping("/catalogue-items")
@RequiredArgsConstructor
public class CatalogueController {

    private final CatalogueService catalogueService;

    @Operation(
        summary = "Create a catalogue item",
        description = """
            Adds a new **Product/Item** to the catalogue.
            
            ### Request Example
            ```json
            {
              "name": "Tata Tea Gold 1kg",
              "particulars": "Groceries",
              "description": "Premium quality tea from the gardens of Assam",
              "url": "https://example.in/images/tata_tea.png",
              "uom": "PIECE",
              "price": {
                "amount": 540.00,
                "currency": "INR"
              }
            }
            ```
            """
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CatalogueItemDto createItem(@RequestBody @Valid CreateCatalogueItemRequest request) {
        return catalogueService.createItem(request);
    }

    @Operation(
        summary = "Update a catalogue item",
        description = """
            Updates an existing product in the catalogue by its ID.
            
            ### Request Example
            ```json
            {
              "name": "Tata Tea Gold 1kg (Special Edition)",
              "particulars": "Groceries",
              "description": "Premium quality tea from the gardens of Assam with festive packaging.",
              "url": "https://example.in/images/tata_tea_festive.png",
              "uom": "PIECE",
              "price": {
                "amount": 599.00,
                "currency": "INR"
              }
            }
            ```
            """
    )
    @PutMapping("/{id}")
    public CatalogueItemDto updateItem(@PathVariable Integer id, @RequestBody @Valid UpdateCatalogueItemRequest request) {
        return catalogueService.updateItem(id, request);
    }

    @Operation(
        summary = "Get a single catalogue item",
        description = "Retrieves the details of a specific catalogue item by its ID."
    )
    @GetMapping("/{id}")
    public CatalogueItemDto getItem(@PathVariable Integer id) {
        return catalogueService.getItem(id);
    }

    @Operation(
        summary = "List all catalogue items",
        description = "Retrieves a comprehensive list of all published items in the catalogue."
    )
    @GetMapping
    public List<CatalogueItemDto> getAllItems() {
        return catalogueService.getAllItems();
    }

    @Operation(
        summary = "Delete a catalogue item",
        description = "Removes a specific catalogue item from the system by its ID."
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable Integer id) {
        catalogueService.deleteItem(id);
    }
}
