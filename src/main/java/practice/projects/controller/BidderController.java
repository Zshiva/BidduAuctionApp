package practice.projects.controller;

import practice.projects.usecase.get.GetUserUseCase;
import practice.projects.usecase.get.GetUserUseCaseRequest;
import freemarker.template.TemplateException;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.ModelAndView;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.sql.SQLException;

@Controller("/")
public class BidderController {

    private final GetUserUseCase getUserUseCase;

    @Inject
    public BidderController(GetUserUseCase getUserUseCase) {
        this.getUserUseCase = getUserUseCase;
    }

    @Get("/bidder")
    public ModelAndView bidder() throws MessagingException, TemplateException, SQLException, IOException {
        GetUserUseCaseRequest request = new GetUserUseCaseRequest();
        return new ModelAndView("bidderdashboard",this.getUserUseCase.execute(request).get());
    }
}
