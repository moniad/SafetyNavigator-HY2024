package org.example.bikesmart.maps.logic;

import lombok.RequiredArgsConstructor;
import org.example.bikesmart.maps.GeoSearchResponse;
import org.example.bikesmart.maps.ReverseGeoSearchResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MapsController {
    private final GeoSearchService geoSearchService;


    @GetMapping("/maps/geocode")
    public GeoSearchResponse getGeocodeFromTourPlanningApi(
            @RequestParam String address) {
        return geoSearchService.geocodeAddress(address);
    }

    @GetMapping("/maps/revgeocode")
    public ReverseGeoSearchResponse getGeocodeFromTourPlanningApi(
            @RequestParam double latitude, @RequestParam double longitude) {
        return geoSearchService.geocodeAddress(latitude, longitude);
    }
}
