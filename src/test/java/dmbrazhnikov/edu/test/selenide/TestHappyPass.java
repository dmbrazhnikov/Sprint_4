package dmbrazhnikov.edu.test.selenide;

import com.codeborne.selenide.SessionStorage;
import dmbrazhnikov.edu.test.model.ClientInfo;
import dmbrazhnikov.edu.test.selenide.pom.ClientInfoPage;
import dmbrazhnikov.edu.test.selenide.pom.HomePage;
import dmbrazhnikov.edu.test.selenide.pom.RentParamsPage;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Happy pass for order")
public class TestHappyPass extends BaseUITest {


    @Order(1)
    @DisplayName("Initiate order wizard")
    @ParameterizedTest
    @ValueSource(strings = {"small button","big button"})
    @SneakyThrows
    void shouldStartOrderWizard(String buttonSize) {
        // Act
        HomePage homePage = open("/", HomePage.class);
        Thread.sleep(3L);
        ClientInfoPage clientInfoPage;
        if (buttonSize.equalsIgnoreCase("small button"))
            clientInfoPage = homePage.startWithSmallBtn();
        else
            clientInfoPage = homePage.startWithBigBtn();
        // Assert
        assertAll("Client info page appearance",
                () -> clientInfoPage.header.shouldBe(visible),
                () -> clientInfoPage.header.shouldHave(text("Для кого самокат")),
                () -> clientInfoPage.inputFieldsAreVisible(),
                () -> clientInfoPage.nextBtn.shouldBe(visible)
        );
    }

    @Disabled
    @Test
    @Order(2)
    @DisplayName("Correct client info is accepted")
    void shouldAcceptCorrectClientInfo() {
        // Arrange
        ClientInfo clientInfo = new ClientInfo("Митхун", "Чакраборти",
                "Одесса, ул. Малая Арнаутская", "+79991112233");
        // Act
        HomePage homePage = open("/", HomePage.class);
        ClientInfoPage clientInfoPage = homePage.startWithSmallBtn();
        clientInfoPage.fillInClientInfo(clientInfo);
        RentParamsPage rentParamsPage = clientInfoPage.proceedToRentParams();
        // Assert
        rentParamsPage.header.shouldBe(visible);
    }
}
