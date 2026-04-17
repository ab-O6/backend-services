package com.example.backend.order.domain.event;
import com.example.backend.order.domain.dto.OrderItemDto;
import java.util.List;
public record OrderPlacedEvent(Integer orderId, List<OrderItemDto> items) {}
