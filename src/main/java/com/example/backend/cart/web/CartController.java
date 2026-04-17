package com.example.backend.cart.web;

import com.example.backend.cart.domain.dto.AddToCartRequest;
import com.example.backend.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public void addToCart(@RequestBody AddToCartRequest request) {
        cartService.addItemToCart(request);
    }

    @PostMapping("/checkout/{customerId}")
    public Integer checkout(@PathVariable Integer customerId) {
        return cartService.checkout(customerId);
    }
}
