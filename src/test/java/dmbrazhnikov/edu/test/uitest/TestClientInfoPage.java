package dmbrazhnikov.edu.test.uitest;

import dmbrazhnikov.edu.test.model.ClientInfoParams;
import dmbrazhnikov.edu.test.pom.ClientInfoPage;
import dmbrazhnikov.edu.test.pom.HomePage;
import dmbrazhnikov.edu.test.pom.RentParamsPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;

import static dmbrazhnikov.edu.test.pom.ClientInfoPage.NEXT_BTN;
import static dmbrazhnikov.edu.test.pom.RentParamsPage.HEADER;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Client info page test set")
public class TestClientInfoPage extends BaseUITest {

    /* @Order is crucial here. It's not the best practice, but this way things are much easier here. */

    private static ClientInfoParams clientInfoParams;
    private ClientInfoPage clientInfoPage;

    @ParameterizedTest
    @Order(1)
    @DisplayName("Correct client info is accepted")
    @CsvSource(value = {"Митхун;Чакраборти;Одесса, ул. Малая Арнаутская;+79991112233"}, delimiter = ';')
    void shouldAcceptCorrectClientInfo(String name, String lastName, String address, String phoneNum) throws InterruptedException {
        // Arrange
        clientInfoPage = new ClientInfoPage(driver);
        HomePage homePage = new HomePage(driver);
        clientInfoParams = new ClientInfoParams(name, lastName, address, phoneNum);
        // Act
        driver.get(BASE_URL);
        homePage.initiateOrderWithSmallBtn();
        clientInfoPage.fillInClientInfo(clientInfoParams);
        clientInfoPage.doClick(NEXT_BTN);
        WebElement header = driver.findElement(HEADER);
        Thread.sleep(5000L);
        // Assert
        System.out.println("Selected subway station name is " + clientInfoParams.getSubwayStationName());
        assertTrue(header.isDisplayed(), "Header text not found");
    }


    @Test
    @Order(2)
    @DisplayName("Next button saved correct client info")
    void shouldSaveCorrectClientInfo() {
        // Arrange
        RentParamsPage rentParamsPage = new RentParamsPage(driver);
        // Act
        rentParamsPage.doClick(RentParamsPage.BACK_BTN);
        // Assert
        assertAll(
                "Grouped assertion of fields",
                () -> assertEquals(clientInfoParams.getName(), clientInfoPage.getElementValue(ClientInfoPage.NAME_INPUT)),
                () -> assertEquals(clientInfoParams.getLastName(), clientInfoPage.getElementValue(ClientInfoPage.LAST_NAME_INPUT)),
                () -> assertEquals(clientInfoParams.getAddress(), clientInfoPage.getElementValue(ClientInfoPage.ADDRESS_INPUT)),
                () -> assertEquals(clientInfoParams.getSubwayStationName(), clientInfoPage.getElementValue(ClientInfoPage.SUBWAY_STATION_INPUT)),
                () -> assertEquals(clientInfoParams.getPhoneNum(), clientInfoPage.getElementValue(ClientInfoPage.PHONE_NUMBER_INPUT))
        );
    }
}
