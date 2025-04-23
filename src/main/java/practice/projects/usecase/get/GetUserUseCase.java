package practice.projects.usecase.get;

import practice.projects.platform.usecase.UseCase;
import freemarker.template.TemplateException;
import jakarta.inject.Singleton;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@Singleton
public class GetUserUseCase implements UseCase<GetUserUseCaseRequest, GetUserUseCaseResponse> {
    @Override
    public Optional<GetUserUseCaseResponse> execute(GetUserUseCaseRequest request) throws MessagingException, TemplateException, SQLException, IOException {
        return Optional.of(new GetUserUseCaseResponse(productList));
    }
}
