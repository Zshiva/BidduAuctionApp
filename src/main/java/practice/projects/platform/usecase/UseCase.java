package practice.projects.platform.usecase;

import practice.projects.repository.ProductEntity;
import practice.projects.repository.UserEntity;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@FunctionalInterface
public interface UseCase<I extends UseCaseRequest, O extends  UseCaseResponse> {
    List<UserEntity> userList = new ArrayList<>();
     List<ProductEntity> productList = new ArrayList<>();
    Optional<O> execute(I request) throws MessagingException, TemplateException, SQLException, IOException;
}
