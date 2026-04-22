package practice.projects.controller;


import practice.projects.controller.payload.LoginUserRequestPayload;
import practice.projects.converter.UserConverter;
import practice.projects.repository.UserEntity;
import practice.projects.repository.jdbc.UserDbRepository;
import practice.projects.repository.mapper.DbMapper;
import practice.projects.usecase.login.LoginUseCase;
import practice.projects.usecase.login.LoginUseCaseRequest;
import freemarker.template.TemplateException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.views.View;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;

@Controller("/users")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class LoginController {
    private final LoginUseCase loginUseCase;
    private final UserDbRepository userDbRepository;

    @Inject
    public LoginController(LoginUseCase loginUseCase, UserDbRepository userDbRepository) {
        this.loginUseCase = loginUseCase;
        this.userDbRepository = userDbRepository;
    }

    @View("login")
    @Get("/login")
    public HttpResponse getUser() {
        return HttpResponse.ok();
    }

    @Post("/login")
    public HttpResponse<Object> loginUser(@Body LoginUserRequestPayload payload) throws MessagingException, TemplateException, SQLException, IOException, InterruptedException {
        LoginUseCaseRequest request = UserConverter.toRequest(payload);
        loginUseCase.execute(request);
        String email = payload.email();
        UserEntity user = userDbRepository.findByEmail(email).map(DbMapper::toDomain).orElse(null);
        if (user != null && user.getRoles() != null) {
            String role = user.getRoles().name();
            if (role.equals("BIDDER")) {
                return HttpResponse.redirect(URI.create("/bidder"));
            } else if (role.equals("SELLER")) {
                return HttpResponse.redirect(URI.create("/seller"));
            }
        }
        return HttpResponse.ok();
    }
}
