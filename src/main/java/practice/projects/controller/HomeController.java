package practice.projects.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

@Controller("/")
public class HomeController {
    @View("home")
    @Get("/")
    public String Product()
    {
        return "admin";

    }

}
