package dmbrazhnikov.edu.test.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePage {

    final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public final void doClick(By locator) {
        WebElement element = driver.findElement(locator);
        new WebDriverWait(driver, 5).until(driver -> (element.isDisplayed()));
        element.click();
    }

    public final void doSendKeys(By locator, String text) {
        WebElement element = driver.findElement(locator);
        new WebDriverWait(driver, 5).until(driver -> (element.isDisplayed()));
        element.sendKeys(text);
    }

    public final void scrollTo(By locator) {
        scrollTo(driver.findElement(locator));
    }

    public final void scrollTo(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}
