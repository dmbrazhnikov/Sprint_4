package ru.yandex.practicum.qa.scooter.webui.model;

import lombok.Getter;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public enum ScooterColor {

    BLACK_PEARL("чёрный жемчуг"), GREY_HOPELESSNESS("серая безысходность");

    @Getter
    private final String name;
    private static final Map<String, ScooterColor> unmodifiableMap;

    ScooterColor(String name) {
        this.name = name;
    }

    static {
        Map<String, ScooterColor> map = new ConcurrentHashMap<String, ScooterColor>();
        for (ScooterColor instance : ScooterColor.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        unmodifiableMap = Collections.unmodifiableMap(map);
    }

    public static ScooterColor get(String name) {
        return unmodifiableMap.get(name.toLowerCase());
    }
}
