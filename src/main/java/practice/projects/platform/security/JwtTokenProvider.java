package practice.projects.platform.security;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.security.token.jwt.config.JwtConfiguration;
import io.micronaut.security.token.jwt.generator.JwtTokenGenerator;
import io.micronaut.security.token.jwt.config.JwtConfigurationProperties;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * JWT Token Provider for generating and managing JWT tokens
 */
@Singleton
public class JwtTokenProvider {
    private final JwtTokenGenerator jwtTokenGenerator;
    private final JwtConfiguration jwtConfiguration;

    @Inject
    public JwtTokenProvider(JwtTokenGenerator jwtTokenGenerator, JwtConfigurationProperties jwtConfiguration) {
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.jwtConfiguration = jwtConfiguration;
    }

    /**
     * Generate a JWT token for a user
     * @param email user email
     * @param roles user roles
     * @return JWT token string
     */
    public String generateToken(String email, List<String> roles) {
        return generateToken(email, roles, null);
    }

    /**
     * Generate a JWT token with custom claims
     * @param email user email
     * @param roles user roles
     * @param customClaims additional claims
     * @return JWT token string
     */
    public String generateToken(String email, List<String> roles, @Nullable Map<String, Object> customClaims) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", email);
        claims.put("email", email);
        claims.put("roles", roles);

        if (customClaims != null) {
            claims.putAll(customClaims);
        }

        Optional<String> token = jwtTokenGenerator.generateToken(claims);
        return token.orElseThrow(() -> new RuntimeException("Failed to generate JWT token"));
    }
}

