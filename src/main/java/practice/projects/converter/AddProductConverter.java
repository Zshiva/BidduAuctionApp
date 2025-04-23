package practice.projects.converter;

import practice.projects.controller.payload.AddProductPayload;
import practice.projects.controller.payload.UpdateProductPayload;
import practice.projects.repository.ProductEntity;
import practice.projects.usecase.addproduct.AddProductUseCaseRequest;
import practice.projects.usecase.updateproduct.UpdateProductUseCaseRequest;
import jakarta.inject.Inject;

public class AddProductConverter {
    private final AddProductUseCaseRequest addProductUseCaseRequest;

    @Inject
    public AddProductConverter(AddProductUseCaseRequest addProductUseCaseRequest) {
        this.addProductUseCaseRequest = addProductUseCaseRequest;
    }
    public static AddProductUseCaseRequest toAddProduct(AddProductPayload payload){
        return new AddProductUseCaseRequest(
                payload.name(),
                payload.description(),
                payload.entryTime(),
                payload.expiryTime(),
                payload.minBiddingAmount());
    }
    public static ProductEntity toProduct(AddProductUseCaseRequest request){
        ProductEntity product = new ProductEntity();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setEntryTime(request.entryTime().toString());
        product.setExpiryTime(request.expiryTime().toString());
        product.setMinBiddingAmount(request.minBiddingAmount());
        return product;
    }
    public static  UpdateProductUseCaseRequest toUpdateProduct(UpdateProductPayload payload){
        return new UpdateProductUseCaseRequest(
                payload.name(),
                payload.amount());
    }
}
