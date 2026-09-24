package dev.engripaye.backend.identity;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class JwtService {

    private final SecretKey secretKey;

    private final long minutes;

    JwtService(@Value("${app.jwt.secret}")
               String secret,
               @Value("${app.jwt.access-token-minutes}")
               long minutes) {
        if(secret.length() < 32) throw new IllegalStateException("APP_JWT_SECRET must contain at least 32 characters");
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));


    }


}
