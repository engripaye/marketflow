package dev.engripaye.backend.business;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
@Entity
@Table(name = "memberships")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Membership {
    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Business role;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    public Membership(Business business, AppUser user, Business role){
        this.business = business;
        this.user = user;
        this.role = role;
    }




}
