package practice.projects.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

@Controller("/")
public class SellerController {
    @View("sellerdashboard")
    @Get("/seller")
    public HttpResponse<Object> Seller()
    {
        return HttpResponse.ok();
    }
}
