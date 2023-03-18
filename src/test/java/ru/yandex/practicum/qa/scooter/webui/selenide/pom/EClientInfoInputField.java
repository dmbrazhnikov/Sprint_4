package ru.yandex.practicum.qa.scooter.webui.selenide.pom;

import lombok.Getter;


public enum EClientInfoInputField {

    FIRST_NAME("* Имя"),
    LAST_NAME("* Фамилия"),
    ADDRESS("* Адрес: куда привезти заказ"),
    PHONE_NUM("* Телефон: на него позвонит курьер");

    @Getter
    private final String placeholder;

    EClientInfoInputField(String placeholder) {
        this.placeholder = placeholder;
    }
}
