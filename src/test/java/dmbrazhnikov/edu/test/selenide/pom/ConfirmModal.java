package dmbrazhnikov.edu.test.selenide.pom;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;


public class ConfirmModal {

    public ConfirmModal() {
        $(".Order_ModalHeader__3FDaJ").shouldBe(Condition.visible).shouldHave(text("Хотите оформить заказ?"));
    }
}
