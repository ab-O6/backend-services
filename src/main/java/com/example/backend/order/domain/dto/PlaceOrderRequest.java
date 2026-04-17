package com.example.backend.order.domain.dto;
import java.util.List;
public record PlaceOrderRequest(Integer customerId, List<OrderItemDto> items) {}
