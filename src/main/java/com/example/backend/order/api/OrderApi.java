package com.example.backend.order.api;

import com.example.backend.order.domain.dto.PlaceOrderRequest;

public interface OrderApi {
    Integer placeOrder(PlaceOrderRequest request);
}
