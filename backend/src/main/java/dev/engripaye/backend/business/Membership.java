package dev.engripaye.backend.business;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "memberships")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Membership {

}
