package practice.projects.usecase.updateproduct;

import practice.projects.platform.usecase.UseCase;
import practice.projects.repository.ProductEntity;
import freemarker.template.TemplateException;
import jakarta.inject.Singleton;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ListIterator;
import java.util.Optional;
@Singleton
public class UpdateProductUseCase implements UseCase<UpdateProductUseCaseRequest, UpdateProductUseCaseResponse> {
    @Override
    public Optional<UpdateProductUseCaseResponse> execute(UpdateProductUseCaseRequest request) throws MessagingException, TemplateException, SQLException, IOException {
        ListIterator<ProductEntity> iterator= productList.listIterator();

        while(iterator.hasNext()){
            ProductEntity product = iterator.next();

            if(request.name().equals(product.getName())){
                ProductEntity updatedProduct = new ProductEntity(request.name(), request.amount());
                iterator.set(updatedProduct);

                return Optional.of(new UpdateProductUseCaseResponse());
            }
        }
        return Optional.empty();
    }
}
