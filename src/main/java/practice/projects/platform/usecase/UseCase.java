package practice.projects.platform.usecase;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
@FunctionalInterface
public interface UseCase<I extends UseCaseRequest, O extends  UseCaseResponse> {
    Optional<O> execute(I request) throws MessagingException, TemplateException, SQLException, IOException;
}
