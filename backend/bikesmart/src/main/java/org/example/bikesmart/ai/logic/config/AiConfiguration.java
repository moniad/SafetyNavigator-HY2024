package org.example.bikesmart.ai.logic.config;

import lombok.extern.slf4j.Slf4j;
import org.example.bikesmart.ai.model.GeocodeAiModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;
import org.example.bikesmart.maps.logic.GeoSearchService;

@Configuration
@Slf4j
public class AiConfiguration {
    @Bean
    Function<GeocodeAiModel.GeocodeSearchRequest, GeocodeAiModel.GeocodeSearchResponse> geocodeSearch(GeoSearchService geoSearchService) {
        log.info("Creating geocode search function");
        return request ->
        {
            log.info("Geocode search request: {}", request);
            return new GeocodeAiModel.GeocodeSearchResponse(geoSearchService.geocodeAddress(request.address()));
        };
    }

    @Bean
    Function<GeocodeAiModel.ReverseGeocodeSearchRequest, GeocodeAiModel.ReverseGeocodeSearchResponse> reverseGeosearch(GeoSearchService geoSearchService) {
        log.info("Creating reverse geocode search function");
        return request ->
        {
            log.info("Geocode reverse search request: {}", request);
            return new GeocodeAiModel.ReverseGeocodeSearchResponse(geoSearchService.geocodeAddress(request.latitude(), request.longitude()));
        };
    }

//    @Bean
//    Function<RouteAiModel.Request, RouteAiModel.Response> getRouteFromApi(RoutingService routingService) {
//        log.info("Creating route function");
//        return request -> {
//            log.info("Route request: {}", request);
//            return new RouteAiModel.Response(routingService.createRouting(request.origin(), request.destination()), LocalDateTime.now());
//        };
//    }



}
