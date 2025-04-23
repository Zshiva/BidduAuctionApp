package practice.projects.usecase.login;

import practice.projects.platform.exception.BidduAuctionErrorMessage;
import practice.projects.platform.exception.BidduAuctionException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class LoginUseCaseTest {
    LoginUseCase loginUseCase;
    @BeforeEach
    public void setUp(){
        loginUseCase = new LoginUseCase();
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