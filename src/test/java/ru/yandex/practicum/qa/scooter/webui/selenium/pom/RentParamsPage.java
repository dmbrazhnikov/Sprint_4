package ru.yandex.practicum.qa.scooter.webui.selenium.pom;

import ru.yandex.practicum.qa.scooter.webui.model.ScooterColor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class RentParamsPage extends BasePage {

    public static final By
            HEADER = By.xpath("//div[starts-with(@class,'Order_Header__') and text()='Про аренду']"),
            BACK_BTN = By.xpath("//button[text()='Назад']"),
            DELIVERY_DATE_INPUT = By.xpath("//input[@placeholder='* Когда привезти самокат']"),
            DATEPICKER = By.className("react-datepicker"),
            DAY_DATEPICKER_BTN = By.xpath("//div[starts-with(@class,'react-datepicker__day ')]"),
            RENTAL_PERIOD_PLACEHOLDER = By.xpath("//div[starts-with(@class,'Dropdown-placeholder')]"),
            COMMENTARY_INPUT = By.xpath("//input[@placeholder='Комментарий для курьера']"),
            PLACE_ORDER_BTN = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");


    public RentParamsPage(WebDriver driver) {
        super(driver);
    }

    public void setCurrentDateAsDeliveryDate() {
        doClick(DELIVERY_DATE_INPUT);
        WebElement datePicker = driver.findElement(DATEPICKER);
        WebElement defaultDate = datePicker.findElement(
                By.xpath("//div[starts-with(@class,'react-datepicker__day ') and @tabindex='0']")
        );
        doClick(defaultDate);
    }

    public void setRentalPeriod(String period) {
        doClick(By.className("Dropdown-control"));
        By dropdownMenu = By.className("Dropdown-menu");
        new WebDriverWait(driver, Duration.ofSeconds(5L)).until(driver -> (driver.findElement(dropdownMenu).isDisplayed()));
        doClick(By.xpath("//div[@class='Dropdown-option' and text()='" + period + "']"));
    }

    public void setColorCheckbox(ScooterColor color) {
        doClick(
            By.xpath("//input[@type='checkbox' and @id='" + color.name() + "']")
        );
    }


}
