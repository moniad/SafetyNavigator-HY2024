package org.example.bikesmart.here.route;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum WalkAttributes {
    OPEN("open"),
    PARK("park");

    private final String value;

    WalkAttributes(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static WalkAttributes fromValue(String value) {
        for (WalkAttributes walkAttributes : WalkAttributes.values()) {
            if (walkAttributes.value.equalsIgnoreCase(value)) {
                return walkAttributes;
            }
        }
        throw new IllegalArgumentException("Unknown enum WalkAttributes " + value);
    }
}
