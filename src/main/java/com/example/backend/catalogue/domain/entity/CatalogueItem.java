package com.example.backend.catalogue.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "catalogue_items")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CatalogueItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name = "particulars", length = 256)
    private String particulars;

    @Column(name = "description", length = 512)
    private String description;

    @Column(name = "image_url", length = 1024)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "uom", length = 128)
    private UOM uom;

    @Embedded
    private Price price;

    public CatalogueItem(String name, String particulars, String description, String url, UOM uom, Price price) {
        this.name = name;
        this.particulars = particulars;
        this.description = description;
        this.url = url;
        this.uom = uom;
        this.price = price;
    }
}
