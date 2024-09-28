package org.example.bikesmart.maps.logic;

import lombok.RequiredArgsConstructor;
import org.example.bikesmart.maps.Location;
import org.example.bikesmart.maps.RoutingResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class RoutingService {
   private final RoutingPathCreator routingPathCreator;
   private final RestTemplate restTemplate;

    public RoutingResponse createRouting(String origin, String destination, String transportMode, String returnParams, String driverSchedule) {
        String routePath = routingPathCreator.createRoutePath(origin, destination, transportMode, returnParams, driverSchedule);
        return restTemplate.getForObject(routePath, RoutingResponse.class);
    }
    public RoutingResponse createRouting(Location origin, Location destination) {
        String routePath = routingPathCreator.createRoutePath(origin, destination, "truck","polyline,summary,actions,instructions");
        return restTemplate.getForObject(routePath, RoutingResponse.class);
    }
}
