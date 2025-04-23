 package practice.projects.usecase.biddingproduct;


import practice.projects.platform.usecase.UseCaseResponse;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

@Introspected
@Serdeable
@RecordBuilder
public record BiddingUseCaseResponse(
        String message
) implements UseCaseResponse {
}