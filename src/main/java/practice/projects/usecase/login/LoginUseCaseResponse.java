package practice.projects.usecase.login;

import practice.projects.platform.usecase.UseCaseResponse;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
@Introspected
@Serdeable
public record LoginUseCaseResponse(
        String message
) implements UseCaseResponse {

}
