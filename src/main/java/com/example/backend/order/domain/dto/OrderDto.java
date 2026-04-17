package com.example.backend.order.domain.dto;

import com.example.backend.order.domain.entity.OrderStatus;
import java.math.BigDecimal;
import java.util.List;

public record OrderDto(Integer id, Integer customerId, OrderStatus status, BigDecimal totalAmount, List<OrderItemDto> items) {}
