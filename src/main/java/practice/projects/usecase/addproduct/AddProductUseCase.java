package practice.projects.usecase.addproduct;


import practice.projects.converter.AddProductConverter;
import practice.projects.platform.exception.BidduAuctionErrorMessage;
import practice.projects.platform.exception.BidduAuctionException;
import practice.projects.platform.usecase.UseCase;
import practice.projects.repository.ProductEntity;
import practice.projects.repository.jdbc.ProductDbRepository;
import practice.projects.repository.mapper.DbMapper;
import freemarker.template.TemplateException;
import jakarta.inject.Singleton;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
@Singleton
public class AddProductUseCase implements UseCase<AddProductUseCaseRequest, AddProductUseCaseResponse> {

     private final ProductDbRepository productDbRepository;

     @Inject
     public AddProductUseCase(ProductDbRepository productDbRepository) {
         this.productDbRepository = productDbRepository;
     }


    private String findByProductSource(String productSource){
        return productSource;
    }

    @Override
    public Optional<AddProductUseCaseResponse> execute(AddProductUseCaseRequest request) throws MessagingException, TemplateException, SQLException, IOException {
        ProductEntity productEntity = AddProductConverter.toProduct(request);
//        var productResponse = AddProductConverter.toResponse(productEntity);
        productDbRepository.save(DbMapper.toDbEntity(productEntity));
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

