package dmbrazhnikov.edu.test.selenium.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends BasePage {

    public static final By
            HOME_PAGE_AREA = By.xpath("//div[starts-with(@class,'Home_HomePage')]"),
            ACCORDION_HEADING = By.className("accordion__button"),
            ACCORDION_PANEL = By.className("accordion__panel"),
            ORDER_BTN_SMALL = By.className("Button_Button__ra12g"),
            ORDER_BTN_BIG = By.cssSelector(".Button_Button__ra12g");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void initiateOrderWithSmallBtn() {
        waitForDisplayed(ORDER_BTN_SMALL);
        getRifOfCookiesPopup();
        doClick(ORDER_BTN_SMALL);
    }

    public void initiateOrderWithBigBtn() throws InterruptedException {
        getRifOfCookiesPopup();
        scrollTo(ORDER_BTN_BIG);
        Thread.sleep(3000L);
        doClick(ORDER_BTN_BIG);
    }
}
