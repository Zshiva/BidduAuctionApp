package practice.projects.controller;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.serde.annotation.Serdeable;
import io.micronaut.views.View;

@Controller("/")
public class HomeController {
    @Get("/")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse<?> home()
    {
        return HttpResponse.ok(new HomeResponse(
            "Biddu Auction App",
            "Welcome to Biddu Auction - A modern auction platform",
            "Please login to continue",
            new LoginLinks(
                "/users/login",
                "/users/register"
            )
        ));
    }

    @View("home")
    @Get("/dashboard")
    @Secured("BIDDER,SELLER")
    public String dashboard()
    {
        return "admin";
    }

    @Introspected
    @Serdeable
    static class HomeResponse {
        public String title;
        public String description;
        public String message;
        public LoginLinks links;

        public HomeResponse(String title, String description, String message, LoginLinks links) {
            this.title = title;
            this.description = description;
            this.message = message;
            this.links = links;
        }
    }

    @Introspected
    @Serdeable
    static class LoginLinks {
        public String login;
        public String register;

        public LoginLinks(String login, String register) {
            this.login = login;
            this.register = register;
        }
    }
}
