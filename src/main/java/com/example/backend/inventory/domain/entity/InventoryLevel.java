package com.example.backend.inventory.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventory_levels")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InventoryLevel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private Integer catalogueItemId;

    @Column(nullable = false)
    private Integer quantity;

    public InventoryLevel(Integer catalogueItemId, Integer quantity) {
        this.catalogueItemId = catalogueItemId;
        this.quantity = quantity;
    }
}
