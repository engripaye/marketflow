package dev.engripaye.backend.catalog;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public final class ProductDtos {

    private ProductDtos() {}
    public record create(@NotBlank @Size(max = 160) String name,
                         @NotBlank @Size(max=80) String sku,
                         @Size(max=1000) String description,
                         @NotNull @DecimalMin("0.00") BigDecimal unitPrice,
                         @Min(0) int reorderLevel){}



}

