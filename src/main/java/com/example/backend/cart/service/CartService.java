package com.example.backend.cart.service;

import com.example.backend.cart.domain.dto.AddToCartRequest;
import com.example.backend.cart.domain.entity.Cart;
import com.example.backend.cart.domain.entity.CartItem;
import com.example.backend.cart.repository.CartRepository;
import com.example.backend.catalogue.api.CatalogueApi;
import com.example.backend.catalogue.domain.dto.CatalogueItemDto;
import com.example.backend.inventory.api.InventoryApi;
import com.example.backend.order.api.OrderApi;
import com.example.backend.order.domain.dto.OrderItemDto;
import com.example.backend.order.domain.dto.PlaceOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CatalogueApi catalogueApi;
    private final InventoryApi inventoryApi;
    private final OrderApi orderApi;

    @Transactional
    public void addItemToCart(AddToCartRequest request) {
        // 1. Sync validation: Catalogue existence
        CatalogueItemDto catalogueItem = catalogueApi.getItem(request.catalogueItemId());
        if (catalogueItem == null) {
            throw new IllegalArgumentException("Item not found in Catalogue");
        }

        // 2. Sync validation: Inventory check
        boolean isAvailable = inventoryApi.checkAvailability(request.catalogueItemId(), request.quantity());
        if (!isAvailable) {
            throw new IllegalStateException("Insufficient inventory for item: " + request.catalogueItemId());
        }

        // 3. Add to cart
        Cart cart = cartRepository.findByCustomerId(request.customerId())
                .orElseGet(() -> new Cart(request.customerId()));

        cart.addItem(new CartItem(
                request.catalogueItemId(),
                request.quantity(),
                catalogueItem.price().amount()
        ));

        cartRepository.save(cart);
    }

    @Transactional
    public Integer checkout(Integer customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found for customer"));

        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cannot checkout an empty cart");
        }

        List<OrderItemDto> orderItems = cart.getItems().stream()
                .map(item -> new OrderItemDto(item.getCatalogueItemId(), item.getQuantity(), item.getPriceSnapshot()))
                .collect(Collectors.toList());

        Integer orderId = orderApi.placeOrder(new PlaceOrderRequest(customerId, orderItems));

        cart.getItems().clear();
        cartRepository.save(cart);

        return orderId;
    }
}
