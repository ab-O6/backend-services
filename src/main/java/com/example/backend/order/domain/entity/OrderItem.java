package com.example.backend.order.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer catalogueItemId;

    private Integer quantity;

    private BigDecimal price;

    public OrderItem(Integer catalogueItemId, Integer quantity, BigDecimal price) {
        this.catalogueItemId = catalogueItemId;
        this.quantity = quantity;
        this.price = price;
    }
}
