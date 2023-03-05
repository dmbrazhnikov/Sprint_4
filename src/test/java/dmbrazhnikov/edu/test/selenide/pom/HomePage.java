package dmbrazhnikov.edu.test.selenide.pom;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class HomePage {

    public HomePage() {
        $(".Home_Header__iJKdX").shouldBe(Condition.visible);
    }

    public ClientInfoPage startWithSmallBtn() {
        $(".Button_Button__ra12g").click();
        return new ClientInfoPage();
    }

    public ClientInfoPage startWithBigBtn() {
        SelenideElement hugeOrderBtn = $(".Button_Button__ra12g.Button_UltraBig__UU3Lp");
        hugeOrderBtn.scrollIntoView(true);
        hugeOrderBtn.click();
        return new ClientInfoPage();
    }

    public void getRidOfCookies() {
        ElementsCollection cookieButtons = $$(".App_CookieButton__3cvqF");
        if (cookieButtons.size() > 0)
            cookieButtons.get(0).click();
    }
}
