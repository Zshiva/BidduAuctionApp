package practice.projects.usecase.login;


import practice.projects.platform.exception.BidduAuctionErrorMessage;
import practice.projects.platform.exception.BidduAuctionException;
import practice.projects.platform.rest.RestResponse;
import practice.projects.platform.usecase.UseCase;
import practice.projects.repository.UserEntity;
import jakarta.inject.Inject;

import java.util.Optional;

public class LoginUseCase implements UseCase<LoginUseCaseRequest, LoginUseCaseResponse> {

    @Inject
    public LoginUseCase() {
    }

    private void validateLogin(LoginUseCaseRequest request) {
        String email = request.email();
        Optional<UserEntity> emailExists = findByEmail(email);
        if (!emailExists.isPresent()) {
            throw new BidduAuctionException(BidduAuctionErrorMessage.EMAIL_ID_NOT_FOUND);
        }
    }

    private Optional<UserEntity> findByEmail(String email) {
        return userList.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public Optional<LoginUseCaseResponse> execute(LoginUseCaseRequest request) {
        validateLogin(request);
        String email = request.email();
        Optional<UserEntity> userDetails = findByEmail(email);
        if (userDetails.isPresent() && userDetails.get().getPassword().equals(request.password())) {
            String roleMessage = userDetails.get().getRoles() != null ? userDetails.get().getRoles().toString() : BidduAuctionErrorMessage.UNKNOWN_ROLE.getMessage();
            return Optional.of(new LoginUseCaseResponse(roleMessage + RestResponse.success().message()));
        } else {
            throw new BidduAuctionException(BidduAuctionErrorMessage.LOGIN_CREDENTIALS_DOES_NOT_MATCH);
        }
    }
}
