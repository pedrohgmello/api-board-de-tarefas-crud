package pedrohgmello.com.github.to_do_list_spring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ROLES_AUTHORITIES = "roles";
    public static final String ISSUER = "API to-do-list";

    private final SecurityProperties securityProperties;
    @Autowired
    public TokenService(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public String geradorDeToken(UserDetails usuario) {
        return Jwts.builder()
                .issuer(ISSUER)
                .subject(usuario.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(gerarDataDeExpiracao())
                .signWith(getChaveDeAssinatura())
                .compact();
    }

    private Date gerarDataDeExpiracao() {
        return Date.from(LocalDateTime.now().plus(Duration.ofMillis(securityProperties.expiration())).toInstant(ZoneOffset.of("-03:00")));
    }

    private SecretKey getChaveDeAssinatura() {
        return Keys.hmacShaKeyFor(securityProperties.secret().getBytes(StandardCharsets.UTF_8));
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(getChaveDeAssinatura())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
