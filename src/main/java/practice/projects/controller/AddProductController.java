package practice.projects.controller;

import practice.projects.controller.payload.AddProductPayload;
import practice.projects.converter.AddProductConverter;
import practice.projects.platform.exception.BidduAuctionErrorMessage;
import practice.projects.platform.exception.BidduAuctionException;
import practice.projects.usecase.addproduct.AddProductUseCase;
import practice.projects.usecase.addproduct.AddProductUseCaseRequest;
import freemarker.template.TemplateException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.views.View;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;

@Controller("/seller")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Secured("SELLER") // Only SELLER role can access this controller
public class AddProductController {
    private final AddProductUseCase addProductUseCase;
    @Inject

    public AddProductController(AddProductUseCase addProductUseCase) {
        this.addProductUseCase = addProductUseCase;
    }

    @View("addproduct")
    @Get("/addproduct")
    public HttpResponse addProduct(){
        return HttpResponse.ok();
    }

    @Post("/addproduct")
        public HttpResponse<Object> addProduct(@Body AddProductPayload payload) throws MessagingException, TemplateException, SQLException, IOException {
        AddProductUseCaseRequest request = AddProductConverter.toAddProduct(payload);
        var response = addProductUseCase.execute(request);
        if(response.isPresent()){
            return HttpResponse.redirect(URI.create("/seller"));
        }else {
            throw new BidduAuctionException(BidduAuctionErrorMessage.PRODUCT_ADDITION_FAILED);
        }
    }
}


