package dmbrazhnikov.edu.test.pom;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


@Slf4j
public class HomePage extends BasePage {

    public final By accordionHeading = By.className("accordion__button");
    public final By accordionPanel = By.className("accordion__panel");

    public HomePage(WebDriver driver) {
        super(driver);
    }

}
