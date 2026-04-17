package com.example.backend.catalogue.service;

import com.example.backend.catalogue.domain.dto.CatalogueItemDto;
import com.example.backend.catalogue.domain.dto.CreateCatalogueItemRequest;
import com.example.backend.catalogue.domain.dto.PriceDto;
import com.example.backend.catalogue.domain.dto.UpdateCatalogueItemRequest;
import com.example.backend.catalogue.domain.entity.CatalogueItem;
import com.example.backend.catalogue.domain.entity.Price;
import com.example.backend.catalogue.domain.event.CatalogueItemCreatedEvent;
import com.example.backend.catalogue.domain.event.CatalogueItemDeletedEvent;
import com.example.backend.catalogue.domain.event.CatalogueItemUpdatedEvent;
import com.example.backend.catalogue.repository.CatalogueItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogueService {

    private final CatalogueItemRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public CatalogueItemDto createItem(CreateCatalogueItemRequest request) {
        CatalogueItem item = new CatalogueItem(
                request.name(),
                request.particulars(),
                request.description(),
                request.url(),
                request.uom(),
                new Price(request.price().amount(), request.price().currency())
        );

        CatalogueItem saved = repository.save(item);
        CatalogueItemDto dto = mapToDto(saved);
        eventPublisher.publishEvent(new CatalogueItemCreatedEvent(dto));
        return dto;
    }

    @Transactional
    public CatalogueItemDto updateItem(Integer id, UpdateCatalogueItemRequest request) {
        CatalogueItem item = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        item.setName(request.name());
        item.setParticulars(request.particulars());
        item.setDescription(request.description());
        item.setUrl(request.url());
        item.setUom(request.uom());
        item.setPrice(new Price(request.price().amount(), request.price().currency()));

        CatalogueItem saved = repository.save(item);
        CatalogueItemDto dto = mapToDto(saved);
        eventPublisher.publishEvent(new CatalogueItemUpdatedEvent(dto));
        return dto;
    }

    @Transactional(readOnly = true)
    public CatalogueItemDto getItem(Integer id) {
        return repository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
    }

    @Transactional(readOnly = true)
    public List<CatalogueItemDto> getAllItems() {
        return repository.findAll().stream().map(this::mapToDto).toList();
    }

    @Transactional
    public void deleteItem(Integer id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Item not found");
        }
        repository.deleteById(id);
        eventPublisher.publishEvent(new CatalogueItemDeletedEvent(id));
    }

    private CatalogueItemDto mapToDto(CatalogueItem item) {
        return new CatalogueItemDto(
                item.getId(),
                item.getName(),
                item.getParticulars(),
                item.getDescription(),
                item.getUrl(),
                item.getUom(),
                new PriceDto(item.getPrice().getAmount(), item.getPrice().getCurrency())
        );
    }
}
