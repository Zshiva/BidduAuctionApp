package practice.projects.usecase.biddingproduct;

import practice.projects.platform.usecase.UseCase;
import practice.projects.repository.ProductEntity;
import freemarker.template.TemplateException;
import jakarta.inject.Singleton;
import jakarta.mail.IllegalWriteException;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@Singleton
public class BiddingUseCase implements UseCase<BiddingUseCaseRequest, BiddingUseCaseResponse> {

    @Override
    public Optional<BiddingUseCaseResponse> execute(BiddingUseCaseRequest request)
            throws MessagingException, TemplateException, SQLException, IOException {
        ProductEntity selectedProduct = findProductByName(request.name());
        if (selectedProduct == null) {
            throw new IllegalWriteException("Product not found");
        }
        int currentAmount = selectedProduct.getMinBiddingAmount();
        int bidAmount = request.amount();

        if (bidAmount <= currentAmount) {
            throw new IllegalWriteException("Bidding price should be more than the current amount");
        } else {
            selectedProduct.setMinBiddingAmount(bidAmount);
            return Optional.of(new BiddingUseCaseResponse("Amount updated"));
        }
    }
    private ProductEntity findProductByName(String productName) {
        for (ProductEntity product : productList) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }
}
