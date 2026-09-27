package dev.engripaye.backend.catalog;

import dev.engripaye.backend.business.Business;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findByBusinessAndNameContainingIgnoreCase(Business business, String query, Pageable pageable);

    Optional<Product> findByIdAndBusiness(UUID id, Business business);
}
