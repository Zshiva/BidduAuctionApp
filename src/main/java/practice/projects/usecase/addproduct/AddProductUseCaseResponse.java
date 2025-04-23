package practice.projects.usecase.addproduct;


import practice.projects.platform.usecase.UseCaseResponse;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
@Introspected
@Serdeable
public record AddProductUseCaseResponse(
//        String message
) implements UseCaseResponse {}
