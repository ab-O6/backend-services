package com.example.backend.order.web;

import com.example.backend.order.domain.dto.OrderDto;
import com.example.backend.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/customer/{customerId}")
    public List<OrderDto> getCustomerOrders(@PathVariable Integer customerId) {
        return orderService.getOrdersForCustomer(customerId);
    }
}
