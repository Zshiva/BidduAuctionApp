package practice.projects.controller.payload;

import practice.projects.platform.constants.UserRole;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
@Introspected
@Serdeable
public record RegisterRequestPayload(
        String name,
        String email,
        String contact,
        String address,
        UserRole roles
) {
}
