package ru.yandex.practicum.qa.scooter.webui.selenium;

import ru.yandex.practicum.qa.scooter.webui.model.ClientInfo;
import ru.yandex.practicum.qa.scooter.webui.selenium.pom.ClientInfoPage;
import ru.yandex.practicum.qa.scooter.webui.selenium.pom.HomePage;
import ru.yandex.practicum.qa.scooter.webui.selenium.pom.RentParamsPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;

import static ru.yandex.practicum.qa.scooter.webui.selenium.pom.ClientInfoPage.NEXT_BTN;
import static ru.yandex.practicum.qa.scooter.webui.selenium.pom.RentParamsPage.HEADER;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Client info page test set")
public class TestClientInfoPage extends BaseUITest {

    /* @Order is crucial here. It's not the best practice, but this way things are much easier here. */

    private static ClientInfo clientInfo;
    private ClientInfoPage clientInfoPage;

    @BeforeAll
    static void setUp() {
        configureDriver(true);
    }

    @ParameterizedTest
    @Order(1)
    @DisplayName("Correct client info is accepted")
    @CsvSource(value = {"Митхун;Чакраборти;Одесса, ул. Малая Арнаутская;+79991112233"}, delimiter = ';')
    void shouldAcceptCorrectClientInfo(String name, String lastName, String address, String phoneNum) throws InterruptedException {
        // Arrange
        clientInfoPage = new ClientInfoPage(driver);
        HomePage homePage = new HomePage(driver);
        clientInfo = new ClientInfo(name, lastName, address, phoneNum);
        // Act
        driver.get(BASE_URL);
        homePage.initiateOrderWithSmallBtn();
        clientInfoPage.fillInClientInfo(clientInfo);
        clientInfoPage.doClick(NEXT_BTN);
        WebElement header = driver.findElement(HEADER);
        Thread.sleep(5000L);
        // Assert
        System.out.println("Selected subway station name is " + clientInfo.getSubwayStationName());
        assertTrue(header.isDisplayed(), "Header text not found");
    }


    @Test
    @Order(2)
    @DisplayName("Next button saved correct client info")
    void shouldSaveCorrectClientInfo() {
        // Arrange
        RentParamsPage rentParamsPage = new RentParamsPage(driver);
        clientInfoPage = new ClientInfoPage(driver);
        // Act
        rentParamsPage.doClick(RentParamsPage.BACK_BTN);
        // Assert
        assertAll(
                "Grouped assertion of fields",
                () -> assertEquals(clientInfo.getFirstName(), clientInfoPage.getElementValue(ClientInfoPage.NAME_INPUT)),
                () -> assertEquals(clientInfo.getLastName(), clientInfoPage.getElementValue(ClientInfoPage.LAST_NAME_INPUT)),
                () -> assertEquals(clientInfo.getAddress(), clientInfoPage.getElementValue(ClientInfoPage.ADDRESS_INPUT)),
                () -> assertEquals(clientInfo.getSubwayStationName(), clientInfoPage.getElementValue(ClientInfoPage.SUBWAY_STATION_INPUT)),
                () -> assertEquals(clientInfo.getPhoneNum(), clientInfoPage.getElementValue(ClientInfoPage.PHONE_NUMBER_INPUT))
        );
    }
}
