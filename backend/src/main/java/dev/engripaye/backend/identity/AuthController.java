package dev.engripaye.backend.identity;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    AuthDtos.AuthResponse register(@Valid @RequestBody AuthDtos.RegisterRequest registerRequest){
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    AuthDtos.AuthResponse login(@Valid @RequestBody AuthDtos.LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

}
