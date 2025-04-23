package practice.projects.usecase.addproduct;

import practice.projects.platform.usecase.UseCaseRequest;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;
import jakarta.inject.Singleton;

@RecordBuilder
@Introspected
@Serdeable
@Singleton
public record AddProductUseCaseRequest(
        String name,
        String description,
        String entryTime,
        String expiryTime,
        int minBiddingAmount
) implements UseCaseRequest {
}
