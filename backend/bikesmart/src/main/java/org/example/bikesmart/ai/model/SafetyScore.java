package org.example.bikesmart.ai.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import org.example.bikesmart.geojsonParsers.GeoJson;
import org.example.bikesmart.maps.Location;

import java.time.LocalDateTime;

public interface SafetyScore {
    @JsonClassDescription("Use it when you want to calculate route between multiple locations. By default, you will be considered safe route for bikes")
    record Request(String elevation) {
    }


    record Response(Score score) {
    }
}
