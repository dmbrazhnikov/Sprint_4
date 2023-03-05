package dmbrazhnikov.edu.test.selenide;

import com.codeborne.selenide.SelenideElement;
import dmbrazhnikov.edu.test.model.ClientInfo;
import dmbrazhnikov.edu.test.selenide.pom.ClientInfoPage;
import dmbrazhnikov.edu.test.selenide.pom.HomePage;
import dmbrazhnikov.edu.test.selenide.pom.RentParamsPage;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static dmbrazhnikov.edu.test.selenide.pom.EClientInfoInputField.*;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Happy pass for order")
public class TestHappyPass extends BaseUITest {

    @Order(1)
    @DisplayName("Initiate order wizard")
    @ParameterizedTest
    @ValueSource(strings = {"small button", "big button"})
    @SneakyThrows
    void shouldStartOrderWizard(String buttonSize) {
        // Act
        HomePage homePage = open("/", HomePage.class);
        ClientInfoPage clientInfoPage;
        if (buttonSize.equalsIgnoreCase("small button"))
            clientInfoPage = homePage.startWithSmallBtn();
        else
            clientInfoPage = homePage.startWithBigBtn();
        // Assert
        assertAll("Client info page appearance",
                () -> assertEquals(clientInfoPage.inputFields.filterBy(visible).size(), 5),
                () -> ClientInfoPage.nextBtn.shouldBe(visible)
        );
    }

    @Test
    @Order(2)
    @DisplayName("Correct client info is accepted")
    void shouldAcceptCorrectClientInfo() {
        // Arrange
        ClientInfo clientInfo = new ClientInfo("Митхун", "Чакраборти",
                "Одесса, ул. Малая Арнаутская", "+79991112233");
        // Act
        HomePage homePage = open("/", HomePage.class);
        homePage.getRidOfCookies();
        ClientInfoPage clientInfoPage = homePage.startWithSmallBtn();
        clientInfoPage.fillInClientInfo(clientInfo);
        clientInfoPage.proceedToRentParams(); // assert inside
    }

    @ParameterizedTest
    @Order(3)
    @DisplayName("Error message is displayed for incorrect input")
    @MethodSource("provideIncorrectClientInfo")
    void shouldDisplayErrorMessageForIncorrectInput(String placeholder, String input) {
        // Act
        HomePage homePage = open("/", HomePage.class);
        homePage.getRidOfCookies();
        ClientInfoPage clientInfoPage = homePage.startWithSmallBtn();
        SelenideElement errorMsg = clientInfoPage.fillInClientDataField(placeholder, input);
        // Assert
        errorMsg.shouldBe(visible);
    }

    private static Stream<Arguments> provideIncorrectClientInfo() {
        return Stream.of(
                Arguments.of(FIRST_NAME.getPlaceholder(), "123456"),
                Arguments.of(FIRST_NAME.getPlaceholder(), "bewsdsvb"),
                Arguments.of(ADDRESS.getPlaceholder(), "-bhbc"),
                Arguments.of(PHONE_NUM.getPlaceholder(), "ghiuiji")
        );
    }

    @Test
    @Order(4)
    @DisplayName("Rent params are accepted")
    void shouldAcceptRentParams() {
        // Arrange
        ClientInfo clientInfo = new ClientInfo("Митхун", "Чакраборти",
                "Одесса, ул. Малая Арнаутская", "+79991112233");
        // Act
        HomePage homePage = open("/", HomePage.class);
        homePage.getRidOfCookies();
        ClientInfoPage clientInfoPage = homePage.startWithSmallBtn();
        clientInfoPage.fillInClientInfo(clientInfo);
        RentParamsPage rentParamsPage = clientInfoPage.proceedToRentParams();
        rentParamsPage.setCurrentDateAsDeliveryDate();
        rentParamsPage.selectRentPeriod("двое суток");
        rentParamsPage.setColorCheckbox("серая безысходность");
        rentParamsPage.setCommentary("А я всё чаще замечаю, что меня как будто кто-то подменил...");
        rentParamsPage.finishOrder();
    }
}
