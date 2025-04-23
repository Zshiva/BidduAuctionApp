package practice.projects.usecase.updateproduct;

import practice.projects.platform.usecase.UseCaseRequest;

public record UpdateProductUseCaseRequest(
        String name,
        int amount
) implements UseCaseRequest {
}
