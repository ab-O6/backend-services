package com.example.backend.cart.domain.dto;

import java.util.List;

public record CartDto(Integer customerId, List<CartItemDto> items) {}
