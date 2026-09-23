package dev.engripaye.backend.identity;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor( access = AccessLevel.PROTECTED)
public class AppUser {
}
