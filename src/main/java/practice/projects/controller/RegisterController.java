package practice.projects.controller;

import practice.projects.controller.payload.RegisterRequestPayload;
import practice.projects.converter.UserConverter;
import practice.projects.platform.exception.BidduAuctionErrorMessage;
import practice.projects.platform.exception.BidduAuctionException;
import practice.projects.usecase.register.RegisterUseCase;
import practice.projects.usecase.register.RegisterUseCaseRequest;
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
public class RegisterController {
    private final RegisterUseCase registerUseCase;
    @Inject
    public RegisterController(RegisterUseCase registerUseCase) {
        this.registerUseCase = registerUseCase;
    }
    @View("signup")
    @Get("/register")
    public String getUser() {
        return "Hello world";
    }

    @Post("/register")
    public HttpResponse<Object> registerUser(@Body RegisterRequestPayload payload) throws IOException, InterruptedException, MessagingException, TemplateException, SQLException {
        RegisterUseCaseRequest request = UserConverter.toRequest(payload);
        var response = registerUseCase.execute(request);
        if(response.isPresent()){
            return HttpResponse.redirect(URI.create("/users/login"));
        }else {
            throw new BidduAuctionException(BidduAuctionErrorMessage.REGISTRATION_FAILED);
        }
    }
}
