package com.example.backend.cart.domain.dto;

import java.math.BigDecimal;

public record CartItemDto(Integer catalogueItemId, Integer quantity, BigDecimal priceSnapshot) {}
