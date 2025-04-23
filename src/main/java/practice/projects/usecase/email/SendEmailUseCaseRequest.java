package practice.projects.usecase.email;

public record SendEmailUseCaseRequest(
        String to,
        String subject,
        String password
) {
}
