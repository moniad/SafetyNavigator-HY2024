package org.example.bikesmart.maps.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.bikesmart.geojsonParsers.GeoJson;
import org.example.bikesmart.maps.Location;
import org.example.bikesmart.maps.RoutingResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Locale;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
@Service
@RequiredArgsConstructor
public class RoutingService {
   private final RoutingPathCreator routingPathCreator;
   private final RestTemplate restTemplate;
   private final ObjectMapper objectMapper;

    public RoutingResponse createRouting(String origin, String destination, String transportMode, String returnParams, String driverSchedule) {
        String routePath = routingPathCreator.createRoutePath(origin, destination, transportMode, returnParams, driverSchedule);
        return restTemplate.getForObject(routePath, RoutingResponse.class);
    }
    public RoutingResponse createRouting(Location origin, Location destination) {
        String routePath = routingPathCreator.createRoutePath(origin, destination, "truck","polyline,summary,actions,instructions");
        return restTemplate.getForObject(routePath, RoutingResponse.class);
    }


    public GeoJson createRouting(List<Double> startCoords, List<Double> endCoords, String profile, int alternativeIdx) throws IOException {
        if (alternativeIdx < 0) {
            throw new IllegalArgumentException("alternativeIdx cannot be negative");
        }

        // Logowanie współrzędnych
        System.out.println("Start Coords: lat=" + startCoords.get(0) + ", lon=" + startCoords.get(1));
        System.out.println("End Coords: lat=" + endCoords.get(0) + ", lon=" + endCoords.get(1));

        // Upewnij się, że kolejność to longitude, latitude
        String lonlats = String.format(Locale.US, "%f,%f|%f,%f",
                startCoords.get(1), startCoords.get(0), // longitude, latitude
                endCoords.get(1), endCoords.get(0));    // longitude, latitude

        // Budowanie URI
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl("https://smartbike.website/brouter")
                .queryParam("lonlats", lonlats)
                .queryParam("profile", profile != null ? profile : "safe")
                .queryParam("alternativeidx", alternativeIdx)
                .queryParam("format", "geojson")
                .build()
                .encode();

        URI BROUTER_URI = uriComponents.toUri();

        // Logowanie URI
        System.out.println("Constructed URI: " + BROUTER_URI);

        // Wykonanie żądania GET
        String jsonData = restTemplate.getForObject(BROUTER_URI, String.class);

        // Parsowanie danych JSON do obiektu GeoJson
        GeoJson geoJson = objectMapper.readValue(jsonData, GeoJson.class);

        return geoJson;
    }



}
