package org.example.bikesmart.geojsonParsers;

import lombok.Data;

import java.util.List;

@Data
public class GeoJson {
    private String type;
    private List<Feature> features;

    // Gettery i settery
}
