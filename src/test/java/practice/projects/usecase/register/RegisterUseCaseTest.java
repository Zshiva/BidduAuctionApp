//package citytech.global.usecase.register;
//
//import citytech.global.platform.constants.UserRole;
//import citytech.global.platform.email.EmailService;
//import citytech.global.platform.utils.helperutils.PasswordGenerator;
//import citytech.global.repository.UserEntity;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//public class RegisterUseCaseTest {
//
//    @Mock
//    private PasswordGenerator passwordGenerator;
//
//    @Mock
//    private EmailService emailService;
//    @InjectMocks
//    @Mock
//    private RegisterUseCase registerUseCase;
//
//    @Test
//    public void testWithInvalidEmail() {
//        RegisterUseCaseRequest request = new RegisterUseCaseRequest("invalidEmail","invalid@gmail","98989898","Godawa", UserRole.SELLER);
//        Assertions.assertThrows(NullPointerException.class, () -> {
//            registerUseCase.execute(request);
//        });
//    }
//
//    @Test
//    public void testWithExistingEmail() {
//        RegisterUseCaseRequest request = new RegisterUseCaseRequest("invalidEmail","invalid@gmail.com","98989898","Godawa", UserRole.SELLER);
//        Mockito.when(registerUseCase.findByEmail(anyString())).thenReturn(Optional.of(new UserEntity()));
//        assertThrows(NullPointerException.class, () -> {
//            registerUseCase.execute(request);
//        });
//        verify(registerUseCase).findByEmail(anyString());
//    }
//
//    @Test
//    public void testSuccessfulRegistration() throws Exception {
//        RegisterUseCaseRequest request = new RegisterUseCaseRequest("invalidEmail","invalid@gmail","98989898","Godawa", UserRole.SELLER);
//        Mockito.when(PasswordGenerator.generateRandomPassword(8))
//                .thenReturn("randomPassword");
//        Optional<RegisterUseCaseResponse> response = registerUseCase.execute(request);
//        assert(response.isPresent());
//        Mockito.verify(PasswordGenerator.generateRandomPassword(anyInt()));
//        Mockito.verify(emailService).sendEmail(any());
//    }
//}