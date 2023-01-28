package dmbrazhnikov.edu.test.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends BasePage {

    public static final By
            ACCORDION_HEADING = By.className("accordion__button"),
            ACCORDION_PANEL = By.className("accordion__panel"),
            ORDER_BTN_SMALL = By.className("Button_Button__ra12g"),
            ORDER_BTN_BIG = By.className("Button_Button__ra12g Button_UltraBig__UU3Lp");

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
