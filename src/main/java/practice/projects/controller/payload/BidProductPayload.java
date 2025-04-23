package practice.projects.controller.payload;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

@Introspected
@Serdeable
@RecordBuilder
public record BidProductPayload(
        String name,
        int minBiddingAmount,
        int amount
        ) {

}
