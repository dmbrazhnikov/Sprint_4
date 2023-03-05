package dmbrazhnikov.edu.test.selenide.pom;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;


public class RentParamsPage {

    public RentParamsPage() {
        $(".Order_Header__BZXOb").shouldBe(visible).shouldHave(text("Про аренду"));
    }

    public void setCurrentDateAsDeliveryDate() {
        $(byAttribute("placeholder", "* Когда привезти самокат")).click();
        $(".react-datepicker__day--today").click();
    }

    public void selectRentPeriod(String period) {
        $(".Dropdown-control").click();
        SelenideElement menuItem = $(".Dropdown-menu").find(byText(period));
        menuItem.scrollIntoView(true);
        menuItem.shouldBe(visible);
        menuItem.click();
    }

    public void setColorCheckbox(String color) {
        $(byText(color)).click();
    }

    public void setCommentary(String text) {
        $(byAttribute("placeholder", "Комментарий для курьера")).setValue(text);
    }

    public ConfirmModal finishOrder() {
        $(".Order_Buttons__1xGrp").find(byText("Заказать")).click();
        return new ConfirmModal();
    }
}
