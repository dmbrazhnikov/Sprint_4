package ru.yandex.practicum.qa.scooter.webui.selenium.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmModal extends BasePage {

    public static final By
            CONFIRM_BTN = By.xpath("//button[text()='Да']"),
            CONFIRM_HEADER = By.xpath("//div[starts-with(@class,'Order_ModalHeader__') and text()='Хотите оформить заказ?']"),
            CONFIRMED_HEADER = By.xpath("//div[starts-with(@class,'Order_ModalHeader__') and text()='Заказ оформлен']"),
            CONFIRMED_MSG = By.cssSelector("div.Order_Text__2broi");

    public ConfirmModal(WebDriver driver) {
        super(driver);
    }
}
