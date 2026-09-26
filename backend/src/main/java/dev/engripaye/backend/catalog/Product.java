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



}
