package com.example.backend.order.web;

import com.example.backend.order.domain.dto.OrderDto;
import com.example.backend.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Order Management", description = "Endpoints for retrieving customer orders")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(
        summary = "Get orders for a customer",
        description = "Retrieves the full order history associated with a specific customer ID."
    )
    @GetMapping("/customer/{customerId}")
    public List<OrderDto> getCustomerOrders(@PathVariable Integer customerId) {
        return orderService.getOrdersForCustomer(customerId);
    }
}
