package practice.projects.usecase.login;

import practice.projects.platform.exception.BidduAuctionErrorMessage;
import practice.projects.platform.exception.BidduAuctionException;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import practice.projects.repository.jdbc.UserDbRepository;

import static org.junit.jupiter.api.Assertions.*;

class LoginUseCaseTest {
    LoginUseCase loginUseCase;

    @BeforeEach
    void setUp() {
        UserDbRepository repo = Mockito.mock(UserDbRepository.class);
        // repository returns empty optional -> EMAIL_ID_NOT_FOUND
        Mockito.when(repo.findByEmail(Mockito.anyString())).thenReturn(java.util.Optional.empty());
        loginUseCase = new LoginUseCase(repo);
    }
//    @Test
//    public void shouldLoginWithRightLoginData(){
//        String email = "bhattarai.suraj110@gmail.com";
//        String password = "MNyQfPBQ";
//        LoginUseCaseRequest request = new LoginUseCaseRequest("bhattarai.suraj110@gmail.com", "MNyQfPBQ");
//        Optional<LoginUseCaseResponse> response = loginUseCase.execute(request);
//        assertTrue(response.isPresent());
//    }
    @Test
    public void shouldNotLoginWhenEmailIsIncorrect(){
        LoginUseCaseRequest request = new LoginUseCaseRequest("bhattarai.suraj110@gmail.com", "pascjvujtg");
        Throwable throwable = assertThrows(BidduAuctionException.class, () -> loginUseCase.execute(request));
        assertEquals(BidduAuctionErrorMessage.EMAIL_ID_NOT_FOUND.getMessage(), throwable.getMessage());
    }
}