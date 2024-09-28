package org.example.bikesmart.geojsonParsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BRouterService {

    @Autowired
    private RestTemplate restTemplate;

    public GeoJson getData() {
        String url = "http://localhost:17777/brouter?lonlats=19.94098,50.06465|19.94428,50.04990&profile=trekking&alternativeidx=0&format=geojson"; // Zastąp rzeczywistym URL

        try {
            // Wykonanie żądania GET
            String jsonData = restTemplate.getForObject(url, String.class);

            // Parsowanie danych JSON
            ObjectMapper objectMapper = new ObjectMapper();
            GeoJson geoJson = objectMapper.readValue(jsonData, GeoJson.class);

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

