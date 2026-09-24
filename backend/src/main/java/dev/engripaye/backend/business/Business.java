package dev.engripaye.backend.business;

import dev.engripaye.backend.identity.AppUser;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "businesses")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Business extends BaseEntity {

    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique=true)
    private String slug;

    @Column(nullable=false)
    private String currency = "NGN";

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="owner_id", nullable=false)
    private AppUser owner;

    public Business(String name, String slug, AppUser owner) {
        this.name = name;
        this.slug = slug;
        this.owner = owner;
    }

}
