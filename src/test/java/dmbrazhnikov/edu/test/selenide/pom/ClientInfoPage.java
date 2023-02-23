package dmbrazhnikov.edu.test.selenide.pom;

import com.codeborne.selenide.As;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dmbrazhnikov.edu.test.model.ClientInfo;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class ClientInfoPage {

    public final SelenideElement
            header = $(".Order_Header__BZXOb"),
            nextBtn = $(".Button_Button__ra12g.Button_Middle__1CSJM");
    public final ElementsCollection inputFields = $$(".Input_InputContainer__3NykH");


    public void fillInClientInfo(ClientInfo clientInfo) {
        $(byAttribute("placeholder", "* Имя")).setValue(clientInfo.getFirstName());
        $(byAttribute("placeholder", "* Фамилия")).setValue(clientInfo.getLastName());
        $(byAttribute("placeholder", "* Адрес: куда привезти заказ")).setValue(clientInfo.getAddress());
        clientInfo.setSubwayStationName(selectRandomSubwayStation());
        $(byAttribute("placeholder", "* Телефон: на него позвонит курьер")).setValue(clientInfo.getPhoneNum());
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
            subwayStation.scrollTo().click();
            subwayStationName = subwayStation.getText();
        }
        return subwayStationName;
    }

    public RentParamsPage proceedToRentParams() {
        nextBtn.click();
        return new RentParamsPage();
    }

    public boolean inputFieldsAreVisible() {
        ElementsCollection visibleInputFields = inputFields.filterBy(Condition.visible);
        return visibleInputFields.size() == inputFields.size();
    }
}
