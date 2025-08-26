package pedrohgmello.com.github.to_do_list_spring.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record SecurityProperties(
        String secret,
        String prefix,
        Long expiration
){
    @Override
    public String secret() {
        return secret;
    }

    @Override
    public String prefix() {
        return prefix;
    }

    @Override
    public Long expiration() {
        return expiration;
    }
}
