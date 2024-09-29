package org.example.bikesmart.here.route;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CarAttributes {
    OPEN("open"),
    NOTHROUGH("nothrough");

    private final String value;

    CarAttributes(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static CarAttributes fromValue(String value) {
        for (CarAttributes carAttributes : CarAttributes.values()) {
            if (carAttributes.value.equalsIgnoreCase(value)) {
                return carAttributes;
            }
        }
        throw new IllegalArgumentException("Unknown enum CarAttributes " + value);
    }
}
