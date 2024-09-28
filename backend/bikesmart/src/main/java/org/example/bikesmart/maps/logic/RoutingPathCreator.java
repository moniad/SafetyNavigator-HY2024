package org.example.bikesmart.maps.logic;

import org.example.bikesmart.maps.Location;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RoutingPathCreator {

    @Value("${maps.here.route.url}")
    private String routeApiUrl;

    @Value("${maps.here.api-key}")
    private String apiKey;

    public String createRoutePath(String origin, String destination, String transportMode, String returnParams, String driverSchedule) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(routeApiUrl)
                .queryParam("apikey", apiKey)
                .queryParam("origin", origin)
                .queryParam("destination", destination)
                .queryParam("return", returnParams)
                .queryParam("transportMode", transportMode)
                .queryParam("driver[schedule]", driverSchedule)
                .build();
        return uriComponents.toUriString();
    }
    public String createRoutePath(String origin, String destination, String transportMode, String returnParams) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(routeApiUrl)
                .queryParam("apikey", apiKey)
                .queryParam("origin", origin)
                .queryParam("destination", destination)
                .queryParam("return", returnParams)
                .queryParam("transportMode", transportMode)
                .build();
        return uriComponents.toUriString();
    }
    public String createRoutePath(Location origin, Location destination, String transportMode, String returnParams) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(routeApiUrl)
                .queryParam("apikey", apiKey)
                .queryParam("origin", origin.getLat() + "," + origin.getLng())
                .queryParam("destination", destination.getLat() + "," + destination.getLng())
                .queryParam("return", returnParams)
                .queryParam("transportMode", transportMode)
                .build();
        return uriComponents.toUriString();
    }
}
