package dev.engripaye.backend.identity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public final class AuthDtos {

    private AuthDtos() {}

    public record RegisterRequest(
            @NotBlank @Size(max=120)
            String fullName,
            @NotBlank @Email
            String email,
            @NotBlank @Size(min=10,max=72)
            String password,
            @NotBlank @Size(max=120)
            String businessName){}


}
