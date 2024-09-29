package org.example.bikesmart.ai.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import org.example.bikesmart.maps.GeoSearchResponse;
import org.example.bikesmart.maps.ReverseGeoSearchResponse;

public interface GeocodeAiModel {

    @JsonClassDescription("Geocode with following pattern <CountryCode>-<PostalCode> <LocalityName>. Example: ES-32911 SAN CIPRIAN DE VIÑAS")
    record GeocodeSearchRequest(String address) {
    }

    record GeocodeSearchResponse(GeoSearchResponse response) {
    }
    @JsonClassDescription("Get reverse geocode for address and adress based on latitude and longitude")
    record ReverseGeocodeSearchRequest(double latitude, double longitude) {
    }

    record ReverseGeocodeSearchResponse(ReverseGeoSearchResponse response) {
    }
}
