package dmbrazhnikov.edu.test.selenium;

import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public abstract class BaseUITest {

    protected static ChromeDriver driver;
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("WebDriver stopped successfully");
        }
    }

    protected static void configureDriver(boolean isHeadless) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        if (isHeadless) {
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--headless");
        } else {
            options.addArguments("start-maximized");
        }
        driver = new ChromeDriver(options);
        System.out.println("WebDriver is good to go");
    }
}
