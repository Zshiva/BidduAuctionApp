package practice.projects.platform.exception;

public enum BidduAuctionErrorMessage {
    REGISTRATION_FAILED("BA001","Registration failed"),
    EMAIL_ID_ALREADY_EXISTS("BA002","Email ID already exists"),
    EMAIL_ID_INVALID("BA003","Email Id is not valid"),
    SOMETHING_WENT_WRONG("BA004","Something went wrong with credentials"),
    LOGIN_UNSUCCESSFUL("BA008","Login is not successful"),
    LOGIN_CREDENTIALS_DOES_NOT_MATCH("BA009","Login Credentials doesn't match"),
    UNKNOWN_ROLE("BA010","Role is unknown"),
    EMAIL_ID_NOT_FOUND("BA011","Email id not found"),
    PRODUCT_SOURCE_ALREADY_PRESENT("BA012","Product source already present"),
    PRODUCT_ADDITION_FAILED("BA013","Product addition failed"),
    PRODUCT_NAME_REQUIRED("BA014", "Product name required"),
    PRODUCT_DESCRIPTION_REQUIRED("BA015", "Product description required"),
    PRODUCT_MIN_BIDDING_AMOUNT_REQUIRED("BA016", "Product minimum bidding amount required"),
    BIDDING_LIMIT_IS_LESS_THAN_ONELAKH("BA017", "bidding limit is less than one lakhs");


    private String code;
    private String message;
    BidduAuctionErrorMessage(String code, String message){
        this.code = code;
        this.message= message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
