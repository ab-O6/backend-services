package com.example.backend.catalogue.domain.event;

import com.example.backend.catalogue.domain.dto.CatalogueItemDto;

public record CatalogueItemUpdatedEvent(CatalogueItemDto item) {}
