package practice.projects.usecase.biddingproduct;

import practice.projects.platform.usecase.UseCaseRequest;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
@Introspected
@Serdeable
public record BiddingUseCaseRequest(
        String name,
        int minBiddingPrice,
        int amount
) implements UseCaseRequest {
}