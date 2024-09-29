package org.example.bikesmart.ai.logic.config;

import lombok.extern.slf4j.Slf4j;
import org.example.bikesmart.ai.model.BrouterTypeClass;
import org.example.bikesmart.ai.model.GeocodeAiModel;
import org.example.bikesmart.ai.model.RouteAiModel;
import org.example.bikesmart.ai.model.RouteBrouteAiModel;
import org.example.bikesmart.maps.logic.RoutingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
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
@Bean
Function<RouteBrouteAiModel.Request, RouteBrouteAiModel.Response> getRouteFromApi(RoutingService routingService) {
    log.info("Creating route function");
    return request -> {
        log.info("Route request: {}", request);
        try {
            // Pobieranie poprawnych współrzędnych
            List<Double> startCoords = List.of(request.origin().getLat(), request.origin().getLng());
            List<Double> endCoords = List.of(request.destination().getLat(), request.destination().getLng());

            // Logowanie współrzędnych dla debugowania
            log.info("Start Coords: lat={}, lon={}", startCoords.get(0), startCoords.get(1));
            log.info("End Coords: lat={}, lon={}", endCoords.get(0), endCoords.get(1));

            return new RouteBrouteAiModel.Response(
                    routingService.createRouting(startCoords, endCoords, BrouterTypeClass.SAFE.getName(), 1),
                    LocalDateTime.now()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
}





}
