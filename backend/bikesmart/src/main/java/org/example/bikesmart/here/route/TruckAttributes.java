package org.example.bikesmart.here.route;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TruckAttributes {
    OPEN("open"),
    NOTHROUGH("nothrough");
    
    private final String value;

    TruckAttributes(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static TruckAttributes fromValue(String value) {
        for (TruckAttributes truckAttributes : TruckAttributes.values()) {
            if (truckAttributes.value.equalsIgnoreCase(value)) {
                return truckAttributes;
            }
        }
        throw new IllegalArgumentException("Unknown enum TruckAttributes " + value);
    }
}
