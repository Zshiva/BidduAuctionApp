package practice.projects.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.views.View;

@Controller("/")
public class SellerController {
    @View("sellerdashboard")
    @Get("/seller")
    @Secured("SELLER") // Only SELLER role can access
    public HttpResponse<Object> Seller()
    {
        return HttpResponse.ok();
    }
}
