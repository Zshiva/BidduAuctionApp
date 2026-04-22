package practice.projects.usecase.register;

import practice.projects.converter.UserConverter;
import practice.projects.platform.email.EmailService;
import practice.projects.platform.exception.BidduAuctionErrorMessage;
import practice.projects.platform.exception.BidduAuctionException;
import practice.projects.platform.usecase.UseCase;
import practice.projects.platform.utils.helperutils.PasswordGenerator;
import practice.projects.repository.UserEntity;
import practice.projects.repository.jdbc.UserDbRepository;
import practice.projects.repository.mapper.DbMapper;
import practice.projects.usecase.email.SendEmailUseCaseRequest;
import freemarker.template.TemplateException;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterUseCase implements UseCase<RegisterUseCaseRequest, RegisterUseCaseResponse> {

    private final PasswordGenerator passwordGenerator;
    private final EmailService emailService;
    private final UserDbRepository userDbRepository;

    @Inject
    public RegisterUseCase(PasswordGenerator passwordGenerator,
                           EmailService emailService,
                           UserDbRepository userDbRepository) {
        this.passwordGenerator = passwordGenerator;
        this.emailService = emailService;
        this.userDbRepository = userDbRepository;
    }


    private void validateRegistration(RegisterUseCaseRequest registerUseCaseRequest) {
        String email = registerUseCaseRequest.email();
        if (!isValidEmail(email)) {
            throw new BidduAuctionException(BidduAuctionErrorMessage.EMAIL_ID_INVALID);
        }
        if (userDbRepository.existsByEmail(email)) {
            throw new BidduAuctionException(BidduAuctionErrorMessage.EMAIL_ID_ALREADY_EXISTS);
        }
    }

    private boolean isValidEmail(String emailId) {
        Dotenv env = Dotenv.load();
        String emailRegex = env.get("EMAIL_REGEX");
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(emailId);
        return matcher.matches();
    }

    @Override
    public Optional<RegisterUseCaseResponse> execute(RegisterUseCaseRequest request) throws MessagingException, TemplateException, SQLException, IOException {
        validateRegistration(request);
        String password = PasswordGenerator.generateRandomPassword(8);
        UserEntity user = UserConverter.toEntity(request, password);
        userDbRepository.save(DbMapper.toDbEntity(user));
        SendEmailUseCaseRequest emailRequest = new SendEmailUseCaseRequest(user.getEmail(), "Welcome to Biddu Auction", password);
        emailService.sendEmail(emailRequest);
        return Optional.of(new RegisterUseCaseResponse());
    }
}
