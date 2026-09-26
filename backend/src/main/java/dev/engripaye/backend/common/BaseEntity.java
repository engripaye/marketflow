package dev.engripaye.backend.common;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    protected UUID id = UUID.randomUUID();

    @Version
    protected long version;

    @Column(nullable = false, updatable = false)
    protected Instant createdAt = Instant.now();

    @Column(nullable = false)
    protected Instant updatedAt = Instant.now();

    @PreUpdate
    protected void touch(){
        updatedAt = Instant.now();
    }

}
