package practice.projects.platform.exception;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class BidduAuctionException extends RuntimeException{
    private final BidduAuctionErrorMessage errorMessage;

    public BidduAuctionException(BidduAuctionErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
    public BidduAuctionErrorMessage getErrorMessage(){
        return errorMessage;
    }
}
