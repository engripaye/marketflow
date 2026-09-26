package dev.engripaye.backend.catalog;

import dev.engripaye.backend.business.Business;
import dev.engripaye.backend.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor( access = AccessLevel.PROTECTED)
@Entity
@Table( name = "products")
public class Product extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String sku;

    private String description;

    @Column(name="reorder_level",nullable=false)
    private int reorderLevel;

    @Column(nullable=false)
    private boolean active=true;

    public Product(String name, String sku, String description, int reorderLevel) {
        this.name = name;
        this.sku = sku;
        this.description = description;
        this.reorderLevel = reorderLevel;
    }



}
