package dmbrazhnikov.edu.test;

import dmbrazhnikov.edu.test.pom.AboutRentPage;
import dmbrazhnikov.edu.test.pom.HomePage;
import dmbrazhnikov.edu.test.pom.ClientInfoPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Client info page")
public class TestClientInfoPage extends BaseTest {

    @Test
    @Order(1)
    @DisplayName("Is displayed")
    void shouldOpenClientInfoPage() {
        // Arrange
        HomePage homePage = new HomePage(driver);
        // Act
        homePage.doClick(HomePage.ORDER_BTN_SMALL);
        WebElement header = driver.findElement(ClientInfoPage.HEADER);
        new WebDriverWait(driver, 5);
        // Assert
        assertTrue(header.isDisplayed());
    }

    @Test
    @Order(2)
    @DisplayName("Correct client info is accepted")
    void shouldAcceptCorrectClientInfo() {
        // Arrange
        ClientInfoPage clientInfoPage = new ClientInfoPage(driver);
        // Act
        clientInfoPage.fillInClientInfo();
        clientInfoPage.doClick(ClientInfoPage.NEXT_BTN);
        WebElement header = driver.findElement(AboutRentPage.HEADER);
        new WebDriverWait(driver, 5);
        // Assert
        assertTrue(header.isDisplayed());
    }
}
