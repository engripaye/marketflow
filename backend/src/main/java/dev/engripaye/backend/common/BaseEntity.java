package dev.engripaye.backend.common;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
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


}
