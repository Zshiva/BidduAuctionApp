package practice.projects.usecase.login;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

/**
 * JWT Token Response DTO
 */
@RecordBuilder
@Introspected
@Serdeable
public record JwtTokenResponse(
        String token,
        String type,
        String email,
        String role,
        Long expiresIn
) {
}

