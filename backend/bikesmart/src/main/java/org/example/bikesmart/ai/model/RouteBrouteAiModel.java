package org.example.bikesmart.ai.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import org.example.bikesmart.geojsonParsers.GeoJson;
import org.example.bikesmart.maps.Location;
import org.example.bikesmart.maps.RoutingResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface RouteBrouteAiModel {
    @JsonClassDescription("Use it when you want to calculate route between multiple locations. By default, you will be considered safe route for bikes")
    record Request(Location origin, Location destination, BrouterTypeClass profile, int alternativeIdx) {
    }


    record Response(GeoJson routingResponse, LocalDateTime now) {
    }

}
