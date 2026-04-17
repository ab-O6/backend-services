package com.example.backend.cart.domain.dto;
public record AddToCartRequest(Integer customerId, Integer catalogueItemId, Integer quantity) {}
