package com.example.backend.order.domain.dto;
import java.math.BigDecimal;
public record OrderItemDto(Integer catalogueItemId, Integer quantity, BigDecimal price) {}
