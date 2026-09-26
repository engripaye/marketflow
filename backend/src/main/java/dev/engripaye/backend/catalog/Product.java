package dev.engripaye.backend.catalog;

import dev.engripaye.backend.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor( access = AccessLevel.PROTECTED)
@Entity
@Table( name = "products")
public class Product extends BaseEntity {

}
