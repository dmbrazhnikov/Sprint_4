package dmbrazhnikov.edu.test.selenium.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public abstract class BasePage {

    final WebDriver driver;

    public static final By COOKIES_CONSENT_BTN = By.id("rcc-confirm-button");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public final void doClick(By locator) {
        doClick(driver.findElement(locator));
    }

    public final void doClick(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(5L)).until(driver -> (element.isDisplayed()));
        element.click();
    }

    public final void doSendKeys(By locator, String text) {
        WebElement element = driver.findElement(locator);
        new WebDriverWait(driver, Duration.ofSeconds(5L)).until(driver -> (element.isDisplayed()));
        element.sendKeys(text);
    }

    public final void doSendKeys(By locator, Keys key) {
        WebElement element = driver.findElement(locator);
        new WebDriverWait(driver, Duration.ofSeconds(5L)).until(driver -> (element.isDisplayed()));
        element.sendKeys(key);
    }

    public final void scrollTo(By locator) {
        scrollTo(driver.findElement(locator));
    }

    public final void scrollTo(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", element);
    }

    public final String getElementValue(By locator) {
        WebElement element = driver.findElement(locator);
        new WebDriverWait(driver, Duration.ofSeconds(5L)).until(driver -> (element.isDisplayed()));
        return element.getAttribute("value");
    }

    public final void waitForDisplayed(By locator) {
        WebElement element = driver.findElement(locator);
        new WebDriverWait(driver, Duration.ofSeconds(5L)).until(driver -> (element.isDisplayed()));
    }

    public final void getRifOfCookiesPopup() {
        List<WebElement> cookiesConsentBtns = driver.findElements(COOKIES_CONSENT_BTN);
        if (cookiesConsentBtns.size() > 0)
            cookiesConsentBtns.get(0).click();
    }

    public final void scrollToTheTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
    }
}
