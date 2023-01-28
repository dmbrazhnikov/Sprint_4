package dmbrazhnikov.edu.test.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AboutRentPage extends BasePage {

    public static final By
            HEADER = By.xpath("//div[starts-with(@class,'Order_Header__') and text()='Про аренду']");

    public AboutRentPage(WebDriver driver) {
        super(driver);
    }
}
