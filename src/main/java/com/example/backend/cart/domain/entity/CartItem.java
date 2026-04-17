package com.example.backend.cart.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private Integer catalogueItemId;

    private Integer quantity;

    private BigDecimal priceSnapshot;

    public CartItem(Integer catalogueItemId, Integer quantity, BigDecimal priceSnapshot) {
        this.catalogueItemId = catalogueItemId;
        this.quantity = quantity;
        this.priceSnapshot = priceSnapshot;
    }
}
