package org.example.bikesmart.ai.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import org.example.bikesmart.maps.Location;
import org.example.bikesmart.maps.RoutingResponse;

import java.time.LocalDateTime;

public interface RouteAiModel {

    @JsonClassDescription("Use it when you want to calculate route between two locations. By default, you will be considered as a truck without a driver schedule.")
    record Request(Location origin, Location destination) {
    }


    record Response(RoutingResponse routingResponse, LocalDateTime now) {
    }


}



