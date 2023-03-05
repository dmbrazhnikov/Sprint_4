package dmbrazhnikov.edu.test.selenide.pom;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dmbrazhnikov.edu.test.model.ClientInfo;

import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static dmbrazhnikov.edu.test.selenide.pom.EClientInfoInputField.*;


public class ClientInfoPage {

    public static final SelenideElement
            header = $(".Order_Header__BZXOb"),
            nextBtn = $(".Button_Button__ra12g.Button_Middle__1CSJM"),
            firstNameInputField = $(byAttribute("placeholder", FIRST_NAME.getPlaceholder())),
            lastNameInputField = $(byAttribute("placeholder", LAST_NAME.getPlaceholder())),
            addressInputField = $(byAttribute("placeholder", ADDRESS.getPlaceholder())),
            phoneNumInputField = $(byAttribute("placeholder", PHONE_NUM.getPlaceholder()));
    public final ElementsCollection inputFields = $$(".Input_InputContainer__3NykH");

    public ClientInfoPage() {
        header.shouldBe(visible).shouldHave(text("Для кого самокат"));
    }

    public void fillInClientInfo(ClientInfo clientInfo) {
        firstNameInputField.setValue(clientInfo.getFirstName());
        lastNameInputField.setValue(clientInfo.getLastName());
        addressInputField.setValue(clientInfo.getAddress());
        clientInfo.setSubwayStationName(selectRandomSubwayStation());
        phoneNumInputField.setValue(clientInfo.getPhoneNum());
    }

    /**
     * Selects a random subway station from dropdown menu.
     * @return selected subway station name
     * */
    public String selectRandomSubwayStation() {
        String subwayStationName = "";
        $(byAttribute("placeholder", "* Станция метро")).click();
        ElementsCollection subwayStations = $$(".Order_SelectOption__82bhS");
        if (subwayStations.size() > 0) {
            SelenideElement subwayStation = subwayStations.get(new Random().nextInt(subwayStations.size()));
            subwayStationName = subwayStation.getText();
            subwayStation.scrollIntoView(true).click();
        }
        return subwayStationName;
    }

    public RentParamsPage proceedToRentParams() {
        nextBtn.click();
        return new RentParamsPage();
    }

    public SelenideElement fillInClientDataField(String placeholder, String input) {
        SelenideElement inputField = $(byAttribute("placeholder", placeholder));
        inputField.setValue(input);
        header.click();
        return inputField.parent().$(".Input_ErrorMessage__3HvIb");
    }
}
