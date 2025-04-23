package practice.projects.usecase.addproduct;


import practice.projects.converter.AddProductConverter;
import practice.projects.platform.exception.BidduAuctionErrorMessage;
import practice.projects.platform.exception.BidduAuctionException;
import practice.projects.platform.usecase.UseCase;
import practice.projects.repository.ProductEntity;
import freemarker.template.TemplateException;
import jakarta.inject.Singleton;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
@Singleton
public class AddProductUseCase implements UseCase<AddProductUseCaseRequest, AddProductUseCaseResponse> {


    private String findByProductSource(String productSource){
        return productSource;
    }

    @Override
    public Optional<AddProductUseCaseResponse> execute(AddProductUseCaseRequest request) throws MessagingException, TemplateException, SQLException, IOException {
        ProductEntity productEntity = AddProductConverter.toProduct(request);
//        var productResponse = AddProductConverter.toResponse(productEntity);
        productList.add(productEntity);
        return Optional.of(new AddProductUseCaseResponse());
    }
    private void validateAddProduct(AddProductUseCaseRequest request){
        if(request.name().isEmpty()){
            throw new BidduAuctionException(BidduAuctionErrorMessage.PRODUCT_NAME_REQUIRED);
        }
        if(request.description().isEmpty()){
            throw new BidduAuctionException(BidduAuctionErrorMessage.PRODUCT_DESCRIPTION_REQUIRED);
        }
        if(request.minBiddingAmount() >= 100000){
            throw new BidduAuctionException(BidduAuctionErrorMessage.BIDDING_LIMIT_IS_LESS_THAN_ONELAKH);
        }
    }
}

