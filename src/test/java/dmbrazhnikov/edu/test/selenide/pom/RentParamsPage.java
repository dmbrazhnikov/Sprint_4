package dmbrazhnikov.edu.test.selenide.pom;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class RentParamsPage {
    @As("Main header")
    @FindBy(css = ".Order_Header__BZXOb")
    public SelenideElement header;


}
