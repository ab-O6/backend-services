package com.example.backend.catalogue.api;

import com.example.backend.catalogue.domain.dto.CatalogueItemDto;

public interface CatalogueApi {
    CatalogueItemDto getItem(Integer id);
}
