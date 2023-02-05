package dmbrazhnikov.edu.test.pom;

import dmbrazhnikov.edu.test.model.ClientInfoParams;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;


public class ClientInfoPage extends BasePage {

    public static final By
            HEADER = By.xpath("//div[starts-with(@class,'Order_Header__') and text()='Для кого самокат']"),
            NAME_INPUT = By.xpath("//input[@placeholder='* Имя']"),
            LAST_NAME_INPUT = By.xpath("//input[@placeholder='* Фамилия']"),
            ADDRESS_INPUT = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']"),
            SUBWAY_STATION_INPUT = By.xpath("//input[@placeholder='* Станция метро']"),
            DROPDOWN_MENU = By.className("select-search__options"),
            SUBWAY_STATION_ITEM = By.className("Order_Text__2broi"),
            PHONE_NUMBER_INPUT = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']"),
            NEXT_BTN = By.xpath("//button[text()='Далее']");

    public ClientInfoPage(WebDriver driver) {
        super(driver);
    }

    public void fillInClientInfo(ClientInfoParams clientInfoParams) {
        doSendKeys(NAME_INPUT, clientInfoParams.getName());
        driver.findElement(LAST_NAME_INPUT).sendKeys(clientInfoParams.getLastName());
        driver.findElement(ADDRESS_INPUT).sendKeys(clientInfoParams.getAddress());
        clientInfoParams.setSubwayStationName(selectRandomSubwayStation());
        driver.findElement(PHONE_NUMBER_INPUT).sendKeys(clientInfoParams.getPhoneNum());
    }

    public String selectRandomSubwayStation() {
        String subwayStationName = "";
        doClick(SUBWAY_STATION_INPUT);
        new WebDriverWait(driver, 5).until(driver -> (driver.findElement(DROPDOWN_MENU).isDisplayed()));
        List<WebElement> subwayStationElements = driver.findElements(SUBWAY_STATION_ITEM);
        if (subwayStationElements.size() > 0) {
            WebElement subwayStationElement = subwayStationElements.get(new Random().nextInt(subwayStationElements.size()));
            scrollTo(subwayStationElement);
            subwayStationName = subwayStationElement.getText();
            subwayStationElement.click();
        }
        return subwayStationName;
    }
}
