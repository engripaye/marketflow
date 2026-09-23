package dev.engripaye.backend.business;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Membership, UUID> {
    Optional<Membership> findFirstByUser(AppUser user);
}
