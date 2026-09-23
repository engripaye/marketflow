package dev.engripaye.backend.identity;

import dev.engripaye.backend.business.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final BusinessRepository businessRepository;
    private final MembershipRepository membershipRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public AuthDtos.AuthResponse register(AuthDtos.RegisterRequest registerRequest){
        String email = registerRequest.email().trim().toLowerCase(Locale.ROOT);

        if (appUserRepository.existsByEmailIgnoreCase(email)) throw new ConflictException("An account already exists for this email");

        AppUser appUser = appUserRepository.save(new AppUser(email,passwordEncoder.encode(registerRequest.password()), registerRequest.fullName().trim()));

        String slug = uniqueSlug(registerRequest.businessName());

        Business business = businessRepository.save(new Business(registerRequest.businessName().trim(), slug, user));

        membershipRepository.save(new Membership(business, user, BusinessRole.OWNER));

        return response(user, business);


    }

}
