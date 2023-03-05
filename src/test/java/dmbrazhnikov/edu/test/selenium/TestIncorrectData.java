package dmbrazhnikov.edu.test.selenium;

import dmbrazhnikov.edu.test.model.ClientInfo;
import dmbrazhnikov.edu.test.model.ScooterColor;
import dmbrazhnikov.edu.test.selenium.pom.ClientInfoPage;
import dmbrazhnikov.edu.test.selenium.pom.HomePage;
import dmbrazhnikov.edu.test.selenium.pom.RentParamsPage;
import dmbrazhnikov.edu.test.selenium.BaseUITest;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static dmbrazhnikov.edu.test.selenium.pom.ClientInfoPage.*;
import static dmbrazhnikov.edu.test.selenium.pom.ConfirmModal.CONFIRM_HEADER;
import static dmbrazhnikov.edu.test.selenium.pom.RentParamsPage.DELIVERY_DATE_INPUT;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Incorrect data input")
public class TestIncorrectData extends BaseUITest {

    @BeforeEach
    void setUp() {
        configureDriver(true);
        driver.get(BASE_URL);
        HomePage homePage = new HomePage(driver);
        homePage.initiateOrderWithSmallBtn();
    }

    @Test
    @Order(1)
    @DisplayName("Incorrect client info is declined")
    void shouldDeclineIncorrectClientInfo() throws InterruptedException {
        // Arrange
        ClientInfoPage clientInfoPage = new ClientInfoPage(driver);
        // Act
        clientInfoPage.doSendKeys(NAME_INPUT, "sdfgh");
        clientInfoPage.doClick(HEADER);
        boolean isVisibleIncorrectNameMsg = driver.findElement(
                By.xpath("//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6' and text()='Введите корректное имя']")).isDisplayed();
        driver.findElement(NAME_INPUT).clear();

        clientInfoPage.doSendKeys(LAST_NAME_INPUT, "Зловыати3");
        clientInfoPage.doClick(HEADER);
        boolean isVisibleIncorrectLastNameMsg = driver.findElement(
                By.xpath("//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6' and text()='Введите корректную фамилию']")).isDisplayed();
        driver.findElement(LAST_NAME_INPUT).clear();

        clientInfoPage.doSendKeys(ADDRESS_INPUT, "JOjdspok");
        clientInfoPage.doClick(HEADER);
        boolean isVisibleIncorrectAddressMsg = driver.findElement(
                By.xpath("//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6' and text()='Введите корректный адрес']")).isDisplayed();
        driver.findElement(ADDRESS_INPUT).clear();

        clientInfoPage.doSendKeys(PHONE_NUMBER_INPUT, "uoeihgois");
        clientInfoPage.doClick(HEADER);
        boolean isVisibleIncorrectPhoneNumMsg = driver.findElement(
                By.xpath("//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6' and text()='Введите корректный номер']")).isDisplayed();
        driver.findElement(PHONE_NUMBER_INPUT).clear();
        // Assert
        assertAll("Error messages are displayed for incorrect input",
                () -> assertTrue(isVisibleIncorrectNameMsg, "Incorrect name value is accepted"),
                () -> assertTrue(isVisibleIncorrectLastNameMsg, "Incorrect last name value is accepted"),
                () -> assertTrue(isVisibleIncorrectAddressMsg, "Incorrect address value is accepted"),
                () -> assertTrue(isVisibleIncorrectPhoneNumMsg, "Incorrect phone number value is accepted")
        );
    }

    @Test
    @Order(2)
    @DisplayName("Incorrect delivery date is declined")
    @SneakyThrows
    void shouldDeclineIncorrectDeliveryDate() throws InterruptedException {
        // Arrange
        ClientInfoPage clientInfoPage = new ClientInfoPage(driver);
        ClientInfo clientInfo = new ClientInfo("Сергей", "Кунжутович",
                "Москва, Мерёмушки", "561612986233");
        clientInfoPage.fillInClientInfo(clientInfo);
        System.out.println("Selected subway station name is " + clientInfo.getSubwayStationName());
        clientInfoPage.doClick(NEXT_BTN);
        Thread.sleep(1000L);
        WebElement header = driver.findElement(RentParamsPage.HEADER);
        RentParamsPage rentParamsPage = new RentParamsPage(driver);
        TimeZone.setDefault(TimeZone.getTimeZone("CET"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.AUGUST, 1, 12, 0);
        Date date = calendar.getTime();
        Instant instant = date.toInstant();
        LocalDateTime ldt = instant.atZone(ZoneId.of("CET")).toLocalDateTime();
        String dateInPastLiteral = ldt.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        // Act
        try {
            rentParamsPage.doSendKeys(DELIVERY_DATE_INPUT, dateInPastLiteral);
            rentParamsPage.doSendKeys(DELIVERY_DATE_INPUT, Keys.ENTER);
            rentParamsPage.setRentalPeriod("двое суток");
            rentParamsPage.setColorCheckbox(ScooterColor.BLACK_PEARL);
            rentParamsPage.doSendKeys(RentParamsPage.COMMENTARY_INPUT, "Комментарий");
            rentParamsPage.doClick(RentParamsPage.PLACE_ORDER_BTN);
        } catch (Exception e) {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("shouldDeclineIncorrectDeliveryDate.png"));
        }
        Thread.sleep(2000L);
        assertFalse(
                driver.findElement(CONFIRM_HEADER).isDisplayed(),
                "Incorrect date is accepted"
        );
    }
}
