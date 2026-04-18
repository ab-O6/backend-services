package com.example.backend.cart.web;

import com.example.backend.cart.domain.dto.AddToCartRequest;
import com.example.backend.cart.domain.dto.CartDto;
import com.example.backend.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cart Management", description = "Endpoints for shopping cart operations and checkout")
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @Operation(
        summary = "Add item to cart",
        description = """
            Adds a specific quantity of a catalogue item to the customer's shopping cart.
            
            ### Request Example
            ```json
            {
              "customerId": 101,
              "catalogueItemId": 42,
              "quantity": 2
            }
            ```
            """
    )
    @PostMapping("/add")
    public void addToCart(@RequestBody AddToCartRequest request) {
        cartService.addItemToCart(request);
    }

    @Operation(
        summary = "Checkout cart",
        description = "Initiates the checkout process for the active cart, effectively turning it into an **Order** and emptying the cart. Payment expected in INR."
    )
    @PostMapping("/checkout/{customerId}")
    public Integer checkout(@PathVariable Integer customerId) {
        return cartService.checkout(customerId);
    }

    @Operation(
        summary = "Get customer's cart",
        description = "Retrieves the current shopping cart contents for a specific customer."
    )
    @GetMapping("/{customerId}")
    public CartDto getCart(@PathVariable Integer customerId) {
        return cartService.getCart(customerId);
    }
}
