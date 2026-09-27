package dev.engripaye.backend.catalog;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public final class ProductDtos {

    private ProductDtos() {}
    public record Create(@NotBlank @Size(max = 160) String name,
                         @NotBlank @Size(max=80) String sku,
                         @Size(max=1000) String description,
                         @NotNull @DecimalMin("0.00") BigDecimal unitPrice,
                         @Min(0) int reorderLevel){}

    public record Update(@NotBlank @Size(max=160) String name,
                         @NotBlank @Size(max=80) String sku,
                         @Size(max=1000) String description,
                         @NotNull @DecimalMin("0.00") BigDecimal unitPrice,
                         @Min(0) int reorderLevel,boolean active){}

    public record View(UUID id,
                       String name,
                       String sku,
                       String description,
                       BigDecimal unitPrice,
                       int reorderLevel,
                       boolean active,
                       int quantity){}

}

