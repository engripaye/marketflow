package dev.engripaye.backend.identity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    private final SecretKey secretKey;

    private final long minutes;

    JwtService(@Value("${app.jwt.secret}")
               String secret,
               @Value("${app.jwt.access-token-minutes}")
               long minutes) {

    }


}
