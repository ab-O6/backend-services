package com.example.backend.catalogue.domain.dto;

import com.example.backend.catalogue.domain.entity.UOM;

public record CatalogueItemDto(
        Integer id,
        String name,
        String particulars,
        String description,
        String url,
        UOM uom,
        PriceDto price
) {}
