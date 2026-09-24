package dev.engripaye.backend.identity;

import dev.engripaye.backend.business.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Business business = businessRepository.save(new Business(registerRequest.businessName().trim(), slug, appUser));

        membershipRepository.save(new Membership(business, appUser, BusinessRole.OWNER));

        return response(appUser, business);

    }

    public AuthDtos.AuthResponse login(AuthDtos.LoginRequest loginRequest){
        AppUser appUser = appUserRepository.findByEmailIgnoreCase(loginRequest.email().trim()).orElseThrow(() -> new ConflictException("Invalid email or password"));

        if(!passwordEncoder.matches(loginRequest.password(), appUser.getPasswordHash())) throw new ConflictException("Invalid email or password");

        Business business = membershipRepository.findFirstByUser(appUser).orElseThrow(() -> new ConflictException("No business membership found")).getBusiness();
        return response(appUser, business);
    }

    private AuthDtos.AuthResponse response(AppUser appUser, Business business){
        return new AuthDtos.AuthResponse(jwtService.issue(appUser), new AuthDtos.UserView(appUser.getId(), appUser.getFullName(), appUser.getEmail()), new AuthDtos.BusinessView(
                business.getId(), business.getName(), business.getSlug(), business.getCurrency()
        ));
    }



}
