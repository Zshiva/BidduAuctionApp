package practice.projects.usecase.login;


import practice.projects.platform.exception.BidduAuctionErrorMessage;
import practice.projects.platform.exception.BidduAuctionException;
import practice.projects.platform.rest.RestResponse;
import practice.projects.platform.security.PasswordHasher;
import practice.projects.platform.usecase.UseCase;
import practice.projects.repository.UserEntity;
import practice.projects.repository.jdbc.UserDbRepository;
import practice.projects.repository.mapper.DbMapper;
import jakarta.inject.Inject;

import java.util.Optional;

public class LoginUseCase implements UseCase<LoginUseCaseRequest, LoginUseCaseResponse> {

    private final UserDbRepository userDbRepository;

    @Inject
    public LoginUseCase(UserDbRepository userDbRepository) {
        this.userDbRepository = userDbRepository;
    }

    private void validateLogin(LoginUseCaseRequest request) {
        String email = request.email();
        if (userDbRepository.findByEmail(email).isEmpty()) {
            throw new BidduAuctionException(BidduAuctionErrorMessage.EMAIL_ID_NOT_FOUND);
        }
    }

    @Override
    public Optional<LoginUseCaseResponse> execute(LoginUseCaseRequest request) {
        validateLogin(request);
        String email = request.email();
        var userDbOpt = userDbRepository.findByEmail(email);
        if (userDbOpt.isPresent()) {
            UserEntity userDetails = DbMapper.toDomain(userDbOpt.get());
            if (PasswordHasher.verifyPassword(request.password(), userDetails.getPassword())) {
                String roleMessage = userDetails.getRoles() != null ? userDetails.getRoles().toString() : BidduAuctionErrorMessage.UNKNOWN_ROLE.getMessage();
                return Optional.of(new LoginUseCaseResponse(roleMessage + RestResponse.success().message()));
            }
            throw new BidduAuctionException(BidduAuctionErrorMessage.LOGIN_CREDENTIALS_DOES_NOT_MATCH);
        } else {
            throw new BidduAuctionException(BidduAuctionErrorMessage.LOGIN_CREDENTIALS_DOES_NOT_MATCH);
        }
    }
}
