package dmbrazhnikov.edu.test.selenide.pom;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class HomePage {

    private final SelenideElement
            smallOrderBtn = $(".Button_Button__ra12g");

    public HomePage() {
        $(".Home_Header__iJKdX").shouldBe(Condition.visible);
    }

    public ClientInfoPage startWithSmallBtn() {
        this.smallOrderBtn.click();
        return new ClientInfoPage();
    }

    @SneakyThrows
    public ClientInfoPage startWithBigBtn() {
        $(".App_CookieButton__3cvqF").click();
        SelenideElement hugeOrderBtn = $(".Button_Button__ra12g.Button_UltraBig__UU3Lp");
        hugeOrderBtn.scrollIntoView(true);
//        JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
//        jse.executeScript("arguments[0].scrollIntoView();", hugeOrderBtn);
        hugeOrderBtn.click();
        return new ClientInfoPage();
    }
}
