package practice.projects.usecase.register;

import practice.projects.platform.constants.UserRole;
import practice.projects.platform.usecase.UseCaseRequest;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
@Introspected
@Serdeable
public record RegisterUseCaseRequest(
        String name,
        String email,
        String contact,
        String address,
        UserRole roles
) implements UseCaseRequest {
}
