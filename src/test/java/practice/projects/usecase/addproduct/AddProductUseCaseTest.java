package practice.projects.usecase.addproduct;

import freemarker.template.TemplateException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@MicronautTest
public class AddProductUseCaseTest {
    AddProductUseCase addProductUseCase;

    @BeforeEach
    public void setUp() {
        addProductUseCase = new AddProductUseCase();
    }

    @Test
    public void shouldAddProductWIthRightProductData() throws MessagingException, TemplateException, SQLException, IOException {
        AddProductUseCaseRequest request = new AddProductUseCaseRequest("Football", "" +
                "It is a round shaped ball played with foot", "10:12", "12:13", 2000);
        Optional<AddProductUseCaseResponse> response = addProductUseCase.execute(request);
        assertTrue(response.isPresent());

    }

//    @Test
//    public void shouldNotAddProductWithEmptyName() throws MessagingException, TemplateException, SQLException, IOException {
//        AddProductUseCaseRequest request = new AddProductUseCaseRequest("", "It is a football." +
//                "It is a round shaped ball played with foot", "10:12", "12:13", 2000);
//        Throwable throwable = assertThrows(BidduAuctionException.class, () -> addProductUseCase.execute(request));
//        assertEquals(BidduAuctionErrorMessage.PRODUCT_NAME_REQUIRED.getMessage(), throwable.getMessage());
//
//    }
}
