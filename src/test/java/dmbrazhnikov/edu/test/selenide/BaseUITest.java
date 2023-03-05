package dmbrazhnikov.edu.test.selenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public abstract class BaseUITest {

    @BeforeAll
    static void setUp() {
        configureDriver();
    }

    @AfterAll
    static void tearDown() {
        WebDriver driver = getWebDriver();
        driver.quit();
    }

    protected static void configureDriver() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://qa-scooter.praktikum-services.ru";
//        Configuration.headless = true;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout=5000;
        if (Configuration.headless)
            Configuration.holdBrowserOpen = true;
    }
}
