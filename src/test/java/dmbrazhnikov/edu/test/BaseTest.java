package dmbrazhnikov.edu.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;


abstract class BaseTest {

    static ChromeDriver driver;
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    @BeforeAll
    static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
//        options.addArguments("--window-size=1920,1080");
//        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        System.out.println("Setup is successful");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
        System.out.println("WebDriver is stopped");
    }
}
