package com.example.backend.catalogue.domain.dto;

import com.example.backend.catalogue.domain.entity.UOM;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCatalogueItemRequest(
        @NotBlank String name,
        String particulars,
        String description,
        String url,
        UOM uom,
        @NotNull PriceDto price
) {}
