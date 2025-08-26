package pedrohgmello.com.github.to_do_list_spring.security;

import io.jsonwebtoken.Claims;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

public record JWTObject(
        String subject,
        String issuer,
        Instant issuedAt,
        Instant expiresAt,
        List<String> roles
) {
    public static JWTObject fromClaims(Claims claims) {
        String subject = claims.getSubject();
        String issuer = claims.getIssuer();
        Instant issuedAt = claims.getIssuedAt().toInstant();
        Instant expiration = claims.getExpiration().toInstant();

        List<String> roles = claims.get("roles", List.class);
        if (roles == null) {
            roles = Collections.emptyList();
        }

        return new JWTObject(subject, issuer, issuedAt, expiration, roles);
    }
}
