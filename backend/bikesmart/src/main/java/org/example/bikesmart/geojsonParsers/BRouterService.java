package org.example.bikesmart.geojsonParsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Service
public class BRouterService {

    @Autowired
    private RestTemplate restTemplate;

    public GeoJson getData() {
        String BROUTER_URL = "https://smartbike.website/brouter?lonlats=19.94098,50.06465|19.94428,50.04990&profile=trekking&alternativeidx=0&format=geojson"; // Zastąp rzeczywistym URL

        try {
            // Wykonanie żądania GET
            String jsonData = restTemplate.getForObject(BROUTER_URL, String.class);

            // Parsowanie danych JSON
            ObjectMapper objectMapper = new ObjectMapper();
            GeoJson geoJson = objectMapper.readValue(jsonData, GeoJson.class);
            List<Pair<Double, Double>> xyList = geoJson.getFeatures().stream()
                    .flatMap(feature -> feature.getGeometry().getCoordinates().stream())
                    .map(coordinateList -> new Pair<>(coordinateList.get(0), coordinateList.get(1)))
                    .toList();

            //call HERE
            // Convert xyList to the format required by the API
            List<Map<String, Double>> trace = xyList.stream()
                    .map(pair -> Map.of("lat", pair.getValue0(), "lng", pair.getValue1()))
                    .toList();

            // Create the JSON body
            String requestBody = objectMapper.writeValueAsString(Map.of("trace", trace));

            // Prepare the API URL and key
            String apiUrl = "https://router.hereapi.com/v8/import?apikey=B1i2fhMekYEaFkkPoVJjErKYaquAglsTyib4of2WPfE&return=polyline,summary,actions,instructions&transportMode=bicycle";

            // Create the HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Create the HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response
            System.out.println("Request: " + requestBody);
            System.out.println("Response status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());


            // Wyciąganie prostych danych
            for (Feature feature : geoJson.getFeatures()) {
                Properties properties = feature.getProperties();
                System.out.println("Twórca: " + properties.getCreator());
                System.out.println("Nazwa: " + properties.getName());
                System.out.println("Długość trasy: " + properties.getTrackLength());
                System.out.println("Całkowity czas: " + properties.getTotalTime());
                // Dalsze przetwarzanie...
            }
            return geoJson;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

