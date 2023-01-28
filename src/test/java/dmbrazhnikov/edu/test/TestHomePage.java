package dmbrazhnikov.edu.test;

import dmbrazhnikov.edu.test.pom.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Home page")
public class TestHomePage extends BaseTest {

    @ParameterizedTest
    @Order(1)
    @DisplayName("FAQ item text is visible after click")
    @ValueSource(ints = {0, 2, 3})
    void accordionItemHasExpectedText(int index) {
        HomePage homePage = new HomePage(driver);
        WebElement heading = driver.findElements(HomePage.ACCORDION_HEADING).get(index),
        panel = driver.findElements(HomePage.ACCORDION_PANEL).get(index);
        homePage.scrollTo(heading);
        heading.click();
        new WebDriverWait(driver, 3).until(driver -> (panel.isDisplayed()));
        assertTrue(panel.isDisplayed());
    }
}
