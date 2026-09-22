package dev.engripaye.backend.business;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusinessRepository extends JpaRepository<Business, UUID> {
    boolean existsBySlug(String slug);
}
