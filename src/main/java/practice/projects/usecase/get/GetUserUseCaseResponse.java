package practice.projects.usecase.get;

import practice.projects.platform.usecase.UseCaseResponse;
import practice.projects.repository.ProductEntity;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;

@RecordBuilder
@Introspected
@Serdeable
public record GetUserUseCaseResponse(List<ProductEntity> productList) implements UseCaseResponse {
}
