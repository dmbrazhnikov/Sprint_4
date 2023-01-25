package dmbrazhnikov.edu.test;

import dmbrazhnikov.edu.test.pom.HomePage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestScooter {

    static ChromeDriver driver;
    private static HomePage homePage;
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    @BeforeAll
    static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(BASE_URL);
        homePage = new HomePage(driver);
        log.info("Setup is successful");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
        System.out.println("WebDriver is stopped");
    }

    @ParameterizedTest
    @Order(1)
    @DisplayName("FAQ item text is visible after click")
    @ValueSource(ints = {0, 2, 3})
    void accordionItemHasExpectedText(int index) {
        WebElement heading = driver.findElements(homePage.accordionHeading).get(index),
        panel = driver.findElements(homePage.accordionPanel).get(index);
        homePage.scrollTo(heading);
        heading.click();
        new WebDriverWait(driver, 3).until(driver -> (panel.isDisplayed()));
        assertTrue(panel.isDisplayed());
    }
}
