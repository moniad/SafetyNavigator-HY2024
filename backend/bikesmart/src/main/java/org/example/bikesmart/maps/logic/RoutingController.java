package org.example.bikesmart.maps.logic;

import lombok.RequiredArgsConstructor;
import org.example.bikesmart.maps.RoutingResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class RoutingController {
    private final RoutingService routingService;

    @GetMapping("/routing")
    public RoutingResponse createRouting(String origin, String destination, String transportMode, String returnParams, String driverSchedule) {
    return routingService.createRouting(origin, destination, transportMode, returnParams, driverSchedule);
    }
}
