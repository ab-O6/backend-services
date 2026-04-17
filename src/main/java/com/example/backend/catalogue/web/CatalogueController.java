package com.example.backend.catalogue.web;

import com.example.backend.catalogue.domain.dto.CatalogueItemDto;
import com.example.backend.catalogue.domain.dto.CreateCatalogueItemRequest;
import com.example.backend.catalogue.domain.dto.UpdateCatalogueItemRequest;
import com.example.backend.catalogue.service.CatalogueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalogue-items")
@RequiredArgsConstructor
public class CatalogueController {

    private final CatalogueService catalogueService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CatalogueItemDto createItem(@RequestBody @Valid CreateCatalogueItemRequest request) {
        return catalogueService.createItem(request);
    }

    @PutMapping("/{id}")
    public CatalogueItemDto updateItem(@PathVariable Integer id, @RequestBody @Valid UpdateCatalogueItemRequest request) {
        return catalogueService.updateItem(id, request);
    }

    @GetMapping("/{id}")
    public CatalogueItemDto getItem(@PathVariable Integer id) {
        return catalogueService.getItem(id);
    }

    @GetMapping
    public List<CatalogueItemDto> getAllItems() {
        return catalogueService.getAllItems();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable Integer id) {
        catalogueService.deleteItem(id);
    }
}
