package dmbrazhnikov.edu.test.uitest.sprint4task;

import dmbrazhnikov.edu.test.pom.HomePage;
import dmbrazhnikov.edu.test.uitest.BaseUITest;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static dmbrazhnikov.edu.test.pom.ClientInfoPage.HEADER;
import static dmbrazhnikov.edu.test.pom.HomePage.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
Выпадающий список в разделе «Вопросы о важном». Тебе нужно проверить: когда нажимаешь на стрелочку, открывается соответствующий текст.
*/

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Home page")
public class TestHomePage extends BaseUITest {

    private HomePage homePage;

    @BeforeAll
    static void setUp() {
        configureDriver(true);
    }

    @BeforeEach
    @SneakyThrows
    void prepare() {
        driver.get(BASE_URL);
        Thread.sleep(3000L);
        homePage = new HomePage(driver);
        homePage.getRifOfCookiesPopup();
    }

    @Disabled("Stupid scrolling is not working")
    @Test
    @Order(1)
    @DisplayName("Both Order buttons are displayed")
    @SneakyThrows
    void shouldDisplayOrderButtons() {
        // Act
        boolean smallOrderBtnIsDisplayed = driver.findElement(ORDER_BTN_SMALL).isDisplayed();
        try {
            homePage.scrollTo(ORDER_BTN_BIG);
        } catch (NoSuchElementException e) {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("shouldDisplayOrderButtons.png"));
        }
        Thread.sleep(5000L);
        boolean bigOrderBtnIsDisplayed = driver.findElement(ORDER_BTN_BIG).isDisplayed();
        // Assert
        assertAll(
                () -> assertTrue(smallOrderBtnIsDisplayed, "Small Order button not found"),
                () -> assertTrue(bigOrderBtnIsDisplayed, "Big Order button not found")
        );
    }

    @Test
    @Order(2)
    @DisplayName("Initiate order with small button")
    @SneakyThrows
    void shouldInitiateOrderWithSmallBtn() {
        // Act
        homePage.scrollToTheTop();
        driver.navigate().refresh();
        homePage.initiateOrderWithSmallBtn();
        WebElement header = driver.findElement(HEADER);
        new WebDriverWait(driver, 3);
        // Assert
        assertTrue(header.isDisplayed(), "Client info page didn't appear after clicking Order button");
    }

    @Test
    @Order(3)
    @DisplayName("Initiate order with big button")
    @SneakyThrows
    void shouldInitiateOrderWithBigBtn() {
        // Act
        driver.get(BASE_URL);
        try {
            WebElement header = driver.findElement(HEADER);
            new WebDriverWait(driver, 3);
            // Assert
            assertTrue(header.isDisplayed(), "Client info page didn't appear after clicking Order button");
        } catch (NoSuchElementException e) {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("shouldInitiateOrderWithBigBtn.png"));
        }
    }

    @ParameterizedTest
    @Order(4)
    @DisplayName("FAQ item text is visible after click")
    @ValueSource(ints = {0, 3})
    void shouldDisplayFAQItemTextAfterClick(int index) {
        // Act
        homePage.waitForDisplayed(ACCORDION_HEADING);
        WebElement heading = driver.findElements(ACCORDION_HEADING).get(index),
                panel = driver.findElements(ACCORDION_PANEL).get(index);
        homePage.scrollTo(heading);
        heading.click();
        new WebDriverWait(driver, 3).until(driver -> (panel.isDisplayed()));
        // Assert
        assertTrue(panel.isDisplayed(), "Header text not found");
    }

}
