package org.example.bikesmart.maps.logic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bikesmart.maps.GeoSearchResponse;
import org.example.bikesmart.maps.ReverseGeoSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeoSearchService {
    private final RestTemplate restTemplate;
    @Value("${maps.here.api-key}")
    private String apiKey;
    @Value("${maps.here.geocode.url}")
    private String geocodeUrl;
    @Value("${maps.here.reverse.geocode.url}")
    private String reverseGeocodeUrl;


    public GeoSearchResponse geocodeAddress(String address) {
        try {
            UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(geocodeUrl)
                    .queryParam("apikey", apiKey)
                    .queryParam("q", address)
                    .buildAndExpand();
            String url = uriComponents.toUriString();
            log.info("Sending request to: " + url);
            GeoSearchResponse response = restTemplate.getForObject(url, GeoSearchResponse.class);
            log.info("Response: " + response);

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while sending request");
        }
    }

    public ReverseGeoSearchResponse geocodeAddress(double latitude, double longitude) {
        try {
            String url = reverseGeocodeUrl + "?at=" + latitude + "," + longitude + "&apikey=" + apiKey;
            log.info("Sending request to: " + url);
            return restTemplate.getForObject(url, ReverseGeoSearchResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while sending request");
        }
    }


}
