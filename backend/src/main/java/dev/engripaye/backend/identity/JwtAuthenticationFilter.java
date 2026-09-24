package dev.engripaye.backend.identity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;


@Component
@RequiredArgsConstructor
class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtServive jwt;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain
            ) throws ServletException, IOException{
        String value = request.getHeader(HttpHeaders.AUTHORIZATION)

                if( value != null && value.startsWith("Bearer ")) {
                    try {
                        UUID id = jwt.parseSubject(value.substring(7));

                        var auth = new UsernamePasswordAuthenticationToken(id, null, java.util.List.of());
                        auth.setDetails();
                    }
                }
    }


}
