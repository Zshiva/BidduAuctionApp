package practice.projects.controller;

import practice.projects.controller.payload.BidProductPayload;
import practice.projects.controller.payload.UpdateProductPayload;
import practice.projects.converter.AddProductConverter;
import practice.projects.platform.exception.BidduAuctionErrorMessage;
import practice.projects.platform.exception.BidduAuctionException;
import practice.projects.platform.rest.RestResponse;
import practice.projects.usecase.biddingproduct.BiddingUseCase;
import practice.projects.usecase.biddingproduct.BiddingUseCaseRequest;
import practice.projects.usecase.get.GetUserUseCase;
import practice.projects.usecase.get.GetUserUseCaseRequest;
import practice.projects.usecase.updateproduct.UpdateProductUseCase;
import practice.projects.usecase.updateproduct.UpdateProductUseCaseRequest;
import freemarker.template.TemplateException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.views.ModelAndView;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.sql.SQLException;

@Controller("/")
public class BidProductController {

    private final GetUserUseCase getUserUseCase;
    private final BiddingUseCase biddingUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    @Inject
    public BidProductController(GetUserUseCase getUserUseCase, BiddingUseCase biddingUseCase, UpdateProductUseCase updateProductUseCase) {
        this.getUserUseCase = getUserUseCase;
        this.biddingUseCase = biddingUseCase;
        this.updateProductUseCase = updateProductUseCase;
    }
    @Get("/bidproduct")
    @Secured("BIDDER") // Only BIDDER role can access
    public ModelAndView bidder() throws MessagingException, TemplateException, SQLException, IOException {
        GetUserUseCaseRequest request = new GetUserUseCaseRequest();
        return new ModelAndView("bidproduct",this.getUserUseCase.execute(request).get());
    }
    @Post("/bidproduct")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Secured("BIDDER") // Only BIDDER role can access
    public HttpResponse<Object> bidProduct(@Body BidProductPayload payload) throws MessagingException, TemplateException, SQLException, IOException {
        BiddingUseCaseRequest request = new BiddingUseCaseRequest(payload.name(), payload.minBiddingAmount(), payload.amount());
        var response = biddingUseCase.execute(request);
        if(response.isPresent()){
            return HttpResponse.ok(RestResponse.success(response.get()));
        }else {
            throw new BidduAuctionException(BidduAuctionErrorMessage.PRODUCT_ADDITION_FAILED);
        }
    }
    @Post("/update/{name}")
    @Secured("SELLER") // Only SELLER role can update products
    public HttpResponse<RestResponse> updateProducts(@Body UpdateProductPayload updateProductPayload) throws MessagingException, TemplateException, SQLException, IOException {
        UpdateProductUseCaseRequest updateProductUseCaseRequest = AddProductConverter.toUpdateProduct(updateProductPayload);
        updateProductUseCase.execute(updateProductUseCaseRequest);
        return HttpResponse.ok(RestResponse.success("Product Update successfully"));

    }



}
